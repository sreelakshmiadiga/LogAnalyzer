package com.example.LogAnalyzer.controller;

import com.example.LogAnalyzer.models.LogReport;
import com.example.LogAnalyzer.service.AiLogAnalyzer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final AiLogAnalyzer aiAnalyzer;

    public LogController(AiLogAnalyzer aiAnalyzer) {
        this.aiAnalyzer = aiAnalyzer;
    }

    @PostMapping("/analyze-file")
    public LogReport uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        // Read file content (simplified for this example)
        String content = new String(file.getBytes());

        // Send the content to our AI service
        return aiAnalyzer.analyze(content);
    }

}
