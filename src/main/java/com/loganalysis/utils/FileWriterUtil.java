package com.loganalysis.utils;

import com.loganalysis.service.dto.LogAnalysisOutputDto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileWriterUtil {
    public static void writeToFile(LogAnalysisOutputDto outputDto)  {
        String outputPath = ConfigReader.getLogOutputFilePath();

        try (FileWriter writer = new FileWriter(outputPath)) {
            // 파일에 결과 출력
            writer.write("최다호출 API KEY:\n");
            writer.write(outputDto.getMostCalledApikey() +"\n\n");
            writer.write("상위 3개의 API Service ID와 각각의 요청 수:\n");
            for (Map.Entry<String, Integer> entry : outputDto.getTopApiServiceIdsValues().entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
            writer.write("\n");
            writer.write("웹브라우저별 사용 비율:\n");
            for (Map.Entry<String, Double> entry : outputDto.getBrowserRatios().entrySet()) {
                writer.write(entry.getKey() + ": " + (int)(entry.getValue()*100) +"%\n");
            }
        } catch (IOException e) {
            // 출력시 에러 발생, 로그 출력
            e.printStackTrace();
        }
    }
}