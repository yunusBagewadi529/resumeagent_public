package com.resumeagent.service;

import com.resumeagent.entity.PasswordHistory;
import com.resumeagent.entity.PasswordResetTokens;
import com.resumeagent.entity.User;
import com.resumeagent.exception.ValueNotFoundException;
import com.resumeagent.repository.PasswordHistoryRepository;
import com.resumeagent.repository.PasswordResetTokensRepository;
import com.resumeagent.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PasswordResetService {

    // 30 minutes expiry (seconds)
    private static final long EXPIRATION_SECONDS = 30 * 60L;

    private final UserRepository userRepository;
    private final PasswordResetTokensRepository passwordResetTokensRepository;
    private final PasswordHistoryRepository passwordHistoryRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Create a password reset token and send reset email (silent if email not found)
     */
    @Transactional
    public void createAndSendPasswordResetToken(String email, jakarta.servlet.http.HttpServletRequest httpRequest)
            throws IOException, MessagingException {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        // Always return silently if user not found (prevents enumeration)
        if (optionalUser.isEmpty()) {
            log.info("Password reset requested for non-registered email: {}", email);
            return;
        }

        User user = optionalUser.get();

        // Invalidate previous unused tokens for user (simple approach)
        try {
            passwordResetTokensRepository.deleteByUserId(user.getId());
        } catch (Exception e) {
            // If deletion method not supported by DB implementation, ignore and continue.
            log.debug("deleteByUserId threw exception (non-fatal): {}", e.getMessage());
        }

        // Generate token
        String token = UUID.randomUUID().toString();

        PasswordResetTokens resetToken = PasswordResetTokens.builder()
                .user(user)
                .token(token)
                .expiresAt(Instant.now().plusSeconds(EXPIRATION_SECONDS))
                .ipAddress(httpRequest != null ? httpRequest.getRemoteAddr() : null)
                .userAgent(httpRequest != null ? httpRequest.getHeader("User-Agent") : null)
                .used(false)
                .build();

        passwordResetTokensRepository.save(resetToken);

        // Send reset email (uses existing templating pipeline; method added below)
        emailService.sendPasswordResetEmail(user.getEmail(), user.getFullName(), token);

        log.info("Password reset token created for user: {}", user.getEmail());
    }

    /**
     * Validate token and reset password
     */
    @Transactional
    public void resetPassword(String token, String newPassword) {
        PasswordResetTokens resetToken = passwordResetTokensRepository.findByToken(token)
                .orElseThrow(() -> new ValueNotFoundException("Token not found"));

        if (!resetToken.isUsable()) {
            throw new ValidationException("Token is invalid or expired");
        }

        User user = resetToken.getUser();

        // Check password history to prevent reuse
        List<PasswordHistory> histories = passwordHistoryRepository.findByUserId(user.getId());
        for (PasswordHistory ph : histories) {
            if (passwordEncoder.matches(newPassword, ph.getPasswordHash())) {
                throw new ValidationException("New password must not match previous passwords");
            }
        }

        // Encode and save new password
        String encoded = passwordEncoder.encode(newPassword);
        user.setPasswordHash(encoded);
        userRepository.save(user);

        // Mark token used
        resetToken.markUsed();
        passwordResetTokensRepository.save(resetToken);

        // Persist password history
        PasswordHistory ph = PasswordHistory.builder()
                .user(user)
                .passwordHash(encoded)
                .build();
        passwordHistoryRepository.save(ph);

        log.info("Password reset successful for user: {}", user.getEmail());
    }
}
