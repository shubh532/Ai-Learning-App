package com.elearning.AILearning.aicontent.gemini;

import com.elearning.AILearning.aicontent.AIContentGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class GeminiTestController {
    private final AIContentGenerator generator;


    private final GeminiProperties geminiProperties;
    private final Environment environment;

    @GetMapping("/config")
    public Map<String, String> config() {

        return Map.of(
                "model", geminiProperties.getModel(),
                "apiKey", String.valueOf(geminiProperties.getApiKey())
        );
    }

    @GetMapping("/gemini")
    public String testGemini() {
        return generator.testPrompt();
    }

}
