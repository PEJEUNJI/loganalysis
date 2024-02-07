package com.loganalysis;

import com.loganalysis.service.LogAnalysisService;
import com.loganalysis.service.LogPrinterService;
import com.loganalysis.service.dto.LogAnalysisOutputDto;
import com.loganalysis.utils.FileReaderUtil;
import java.io.IOException;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        // Config 파일로부터 파일 경로 읽어오기
        try {
            Stream<String> lines = FileReaderUtil.readFileLines();

            // LogAnalysisService를 이용하여 로그 데이터 분석
            LogAnalysisService logAnalysisService = new LogAnalysisService();
            LogAnalysisOutputDto outputDto = logAnalysisService.analyze(lines);

            // LogPrinterService를 이용하여 결과 출력
            LogPrinterService logPrinterService = new LogPrinterService();
            logPrinterService.printResults(outputDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}