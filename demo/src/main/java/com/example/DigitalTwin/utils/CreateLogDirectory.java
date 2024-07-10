package com.example.DigitalTwin.utils;

import java.io.File;

public class CreateLogDirectory {

    public static void createLogDirectory() {
        File logDirectory = new File("logs");
        if (!logDirectory.exists()) {
            logDirectory.mkdirs();
        }
    }
}
