package com.example.LogAnalyzer.utility;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@UtilityClass
public class CommonUtil {

    public static String findFilePath(String fileName) throws IOException {
        // Search starting from the project root
        try (var stream = Files.walk(Paths.get("."))) {
            return stream
                    .filter(p -> p.getFileName().toString().equalsIgnoreCase(fileName))
                    .findFirst()
                    .map(Path::toAbsolutePath)
                    .map(Path::toString)
                    .orElseThrow(() -> new RuntimeException("Could not find file: " + fileName));
        }
    }
}
