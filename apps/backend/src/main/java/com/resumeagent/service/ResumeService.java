package com.resumeagent.service;

import com.resumeagent.ai.agents.JobDescriptionAnalyzerAgent;
import com.resumeagent.dto.response.CommonResponse;
import com.resumeagent.entity.User;
import com.resumeagent.entity.model.JobDescriptionAnalyzerJson;
import com.resumeagent.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final UserRepository userRepository;
    private final JobDescriptionAnalyzerAgent jobDescriptionAnalyzerAgent;

    @Transactional
    public CommonResponse generateResume(String jobDescription, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Authenticated user not found"));

        JobDescriptionAnalyzerJson jobDescriptionAnalyzerJson = jobDescriptionAnalyzerAgent.run(jobDescription);

        return null;
    }
}
