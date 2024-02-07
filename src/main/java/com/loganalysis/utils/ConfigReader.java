package com.loganalysis.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final String CONFIG_FILE_PATH = "config.properties";
    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE_PATH)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 분석해야할 로그 파일이 있는 경로
    public static String getLogInputFilePath() {
        return properties.getProperty("inputFilePath");
    }
    // ouput으로 생성된 로그파일이 생성될 경로
    public static String getLogOutputFilePath() {
        return properties.getProperty("outputFilePath");
    }
}