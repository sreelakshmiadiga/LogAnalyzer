package com.example.LogAnalyzer.service;

import com.example.LogAnalyzer.models.LogReport;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiLogAnalyzer {

    private final ChatClient chatClient;

    public AiLogAnalyzer(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("You are a Senior Java Architect. Analyze the log provided and return a structured report.Extract the Java filename from the stack trace (e.g., UserController.java) and include it in the 'fileName' field of the JSON.")
                .build();
    }

    public LogReport analyze(String logData) {
        return chatClient.prompt()
                .system("""
                You are a Log Extraction Tool. 
                Your goal is to find the JAVA SOURCE FILE where the error occurred.
                
                RULES:
                1. Look for the first 'at' line in the stack trace (e.g., at com.example.PaymentController.process(PaymentController.java:13)).
                2. Extract ONLY the filename: 'PaymentController.java'.
                3. If you cannot find a .java file, do not leave it null; guess based on the error message.
                4. Respond ONLY with this JSON structure:
                {"errorType": "...", "rootCause": "...", "fileName": "...", "severity": "..."}
                """)
                .user("Extract info from this log: " + logData)
                .call()
                .entity(LogReport.class);
    }
}
