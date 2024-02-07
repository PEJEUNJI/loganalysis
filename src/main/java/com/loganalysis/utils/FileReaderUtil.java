package com.loganalysis.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileReaderUtil {
    //로그 파일 경로에서 데이터를 읽어와서 해당 파일의 각 라인을 스트림으로 반환
    public static Stream<String> readFileLines() throws IOException {
        String filePath = ConfigReader.getLogInputFilePath();
        Path path = Path.of(filePath);
        return Files.lines(path);
    }
}