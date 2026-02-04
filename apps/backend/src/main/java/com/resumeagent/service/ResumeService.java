package com.resumeagent.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.resumeagent.ai.agents.ATSOptimizationAgent;
import com.resumeagent.ai.agents.JobDescriptionAnalyzerAgent;
import com.resumeagent.ai.agents.MatchingAgent;
import com.resumeagent.ai.agents.ResumeRewriteAgent;
import com.resumeagent.dto.response.CommonResponse;
import com.resumeagent.entity.MasterResume;
import com.resumeagent.entity.Resume;
import com.resumeagent.entity.User;
import com.resumeagent.entity.enums.ResumeStatus;
import com.resumeagent.entity.model.JobDescriptionAnalyzerJson;
import com.resumeagent.entity.model.MasterResumeJson;
import com.resumeagent.entity.model.MatchingAgentJson;
import com.resumeagent.repository.MasterResumeRepository;
import com.resumeagent.repository.ResumeRepository;
import com.resumeagent.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResumeService {

    // Repositories
    private final UserRepository userRepository;
    private final MasterResumeRepository masterResumeRepository;

    // AI Agents
    private final JobDescriptionAnalyzerAgent jobDescriptionAnalyzerAgent;
    private final ResumeRepository resumeRepository;
    private final MatchingAgent matchingAgent;
    private final ResumeRewriteAgent resumeRewriteAgent;
    private final ATSOptimizationAgent atsOptimizationAgent;

    /**
     * Generates a tailored resume based on the provided job description
     * for the authenticated user.
     *
     * @param jobDescription The job description to tailor the resume for.
     * @param email          The email of the authenticated user.
     * @return A CommonResponse indicating success or failure.
     * @throws JsonProcessingException If there is an error processing JSON.
     */
    @Transactional
    public CommonResponse generateResume(String jobDescription, String email) throws JsonProcessingException {

        // Fetch authenticated user
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Authenticated user not found"));

        // Fetch user's master resume
        MasterResume masterResume =  masterResumeRepository.findByUser(user).orElseThrow(
                () -> new IllegalStateException("Master resume not found"));

        // Extract master resume JSON model
        MasterResumeJson masterResumeJson = masterResume.getResumeJson();

        // Execute AI pipeline
        // Step 1: Job Description Analysis
        JobDescriptionAnalyzerJson jobDescriptionAnalyzerJson =
                jobDescriptionAnalyzerAgent.executeJobDescriptionAnalyzerAgent(jobDescription);

        System.out.println("Job Description Analysis JSON: " + jobDescriptionAnalyzerJson);
        // Step 2: Matching
        MatchingAgentJson matchingAgentJson =
                matchingAgent.executeMatchingAgent(masterResumeJson, jobDescriptionAnalyzerJson);

        // Step 3: Resume Rewriting
        MasterResumeJson rewrittenResume = resumeRewriteAgent.executeResumeRewriteAgent(
                masterResumeJson, jobDescriptionAnalyzerJson, matchingAgentJson);

        // Step 4: ATS Optimization
        MasterResumeJson finalResume = atsOptimizationAgent.executeATSOptimizationAgent(rewrittenResume);

        // Extract targeted job title and company name
        String jobTitle = jobDescriptionAnalyzerJson.getJobIdentity().getJobTitle();
        String companyName = jobDescriptionAnalyzerJson.getJobIdentity().getCompanyName();

        // Save the generated resume
        Resume generatedResume = Resume.builder()
                .user(user)
                .masterResume(masterResume)
                .jobTitleTargeted(jobTitle)
                .jobDescriptionAnalyzerJson(jobDescriptionAnalyzerJson)
                .companyTargeted(companyName)
                .resumeJson(finalResume)
                .status(ResumeStatus.ACTIVE)
                .build();

        // Handle potential data integrity issues
        try {
            // Save generated resume
            resumeRepository.save(generatedResume);
        } catch (DataIntegrityViolationException ex) {
            // This handles race conditions or other integrity issues
            throw new RuntimeException("Failed to save generated resume", ex);
        }

        // Return success response
        return CommonResponse.builder()
                .message("Resume generated successfully")
                .email(email)
                .build();
    }
}
