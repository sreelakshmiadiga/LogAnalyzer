package com.example.LogAnalyzer.models;

public record LogReport(
        String errorType,
        String rootCause,
        String suggestedFix,
        String fileName,
        String severity
) {}
