package com.resumeagent.ai.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class PromptLoader {

    public String load(String promptName) {
        try {
            ClassPathResource resource =
                    new ClassPathResource("prompts/" + promptName);

            return new String(
                    resource.getInputStream().readAllBytes(),
                    StandardCharsets.UTF_8
            );
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Failed to load prompt: " + promptName, e
            );
        }
    }
}
