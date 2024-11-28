package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));

    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {

        String ffprobe = "C:\\ffmpeg-7.1-full_build\\bin\\ffprobe.exe";
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(ffprobe, "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath());
        Process process = processBuilder.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String currentString = "";
            while (true) {
                currentString = reader.readLine();
                if (currentString.contains("format.tags.title=")) {
                    return currentString.substring(19).replace("\"", "");
                }
            }
        }
    }
}
