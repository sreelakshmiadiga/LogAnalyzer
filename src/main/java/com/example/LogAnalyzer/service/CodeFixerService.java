package com.example.LogAnalyzer.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CodeFixerService {

    private final ChatClient chatClient;

    public CodeFixerService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public void applyFix(String filePath, String rootCause) throws IOException {
        Path path = Paths.get(filePath);
        String originalCode = Files.readString(path);

        String fixedCode = chatClient.prompt()
                .system("""
                You are an automated refactoring tool. 
                STRICT RULE: Return ONLY the raw source code. 
                NO explanations. NO markdown formatting. NO backticks.
                """)
                .user("Root Cause: " + rootCause + "\n\nOriginal Code:\n" + originalCode)
                .call()
                .content();

        // Safety check: Strip markdown if the AI ignored the instructions
        if (fixedCode.contains("```")) {
            fixedCode = fixedCode.replaceAll("```java|```", "").trim();
        }

        Files.writeString(path, fixedCode);
    }
}
