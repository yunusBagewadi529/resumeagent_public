package com.resumeagent.repository;

import com.resumeagent.entity.PasswordResetTokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PasswordResetTokensRepository extends JpaRepository<PasswordResetTokens, UUID> {
    Optional<PasswordResetTokens> findByToken(String token);
    List<PasswordResetTokens> findByUserIdAndUsedFalse(UUID userId);
    void deleteByUserId(UUID userId);
}
