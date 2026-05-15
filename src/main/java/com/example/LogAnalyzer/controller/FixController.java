package com.example.LogAnalyzer.controller;

import com.example.LogAnalyzer.models.LogReport;
import com.example.LogAnalyzer.service.AiLogAnalyzer;
import com.example.LogAnalyzer.service.CodeFixerService;
import com.example.LogAnalyzer.utility.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
public class FixController {

    @Autowired
    CodeFixerService fixerService;

    @Autowired
    AiLogAnalyzer aiAnalyzer;

    @PostMapping("/fix")
    public String fixError(@RequestParam String filePath, @RequestParam String rootCause) {
        try {
            fixerService.applyFix(filePath, rootCause);
            return "Fix applied successfully to " + filePath;
        } catch (Exception e) {
            return "Failed to apply fix: " + e.getMessage();
        }
    }

    @PostMapping("/auto-fix")
    public String analyzeAndFix(@RequestParam("file") MultipartFile logFile) throws Exception {
        // 1. Analyze the log
        String logContent = new String(logFile.getBytes());
        LogReport report = aiAnalyzer.analyze(logContent);

        // 2. Locate the source file on your disk
        String absolutePath = CommonUtil.findFilePath(report.fileName());

        // 3. Apply the fix
        fixerService.applyFix(absolutePath, report.rootCause());

        return "Successfully analyzed " + report.errorType() + " and applied fix to " + absolutePath;
    }
}
