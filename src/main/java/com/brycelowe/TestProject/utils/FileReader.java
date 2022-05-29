package com.brycelowe.TestProject.utils;


import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {
    public static String[] getLines(Path filePath) throws Exception {
        if (Files.notExists(filePath)) {
            return new String[0];
        }

        return Files.readAllLines(filePath, StandardCharsets.UTF_8).toArray(new String[0]);
    }
}
