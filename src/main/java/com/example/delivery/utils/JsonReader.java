package com.example.delivery.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsonReader {
    public static String loadJsonFromFile(String filePath) throws IOException {
        return IOUtils.toString(JsonReader.class.getClassLoader().getResourceAsStream(filePath), StandardCharsets.UTF_8);
    }
}
