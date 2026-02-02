package com.resumeagent.ai.agents;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resumeagent.ai.llm.LlmClient;
import com.resumeagent.ai.util.PromptLoader;
import com.resumeagent.entity.model.JobDescriptionAnalyzerJson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobDescriptionAnalyzerAgent {

    private final LlmClient lllm;
    private final ObjectMapper objectMapper;
    private final PromptLoader promptLoader;

    public JobDescriptionAnalyzerJson run(String jobDescription) {
        String basePrompt = promptLoader.load("job_description_analyzer.prompt");

        String finalPrompt = basePrompt.replace(
                "{{JOB_DESCRIPTION}}",
                jobDescription
        );

        String output = lllm.generate(finalPrompt);

        String json = sanitizeJson(output);

        try {
            System.out.println(json);
            return objectMapper.readValue(json, JobDescriptionAnalyzerJson.class);
        } catch (Exception e) {
            throw new RuntimeException(
                    "JobDescriptionAnalyzerAgent produced invalid JobDescriptionAnalyzerJson",
                    e
            );
        }
    }

    private String sanitizeJson(String raw) {
        String trimmed = raw.trim();

        if (trimmed.startsWith("```")) {
            trimmed = trimmed
                    .replaceFirst("^```[a-zA-Z]*", "")
                    .replaceFirst("```$", "")
                    .trim();
        }

        return trimmed;
    }
}
