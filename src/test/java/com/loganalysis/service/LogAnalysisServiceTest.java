package com.loganalysis.service;

import com.loganalysis.service.dto.LogAnalysisOutputDto;
import org.junit.jupiter.api.Test;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogAnalysisServiceTest {

    @Test
    public void testAnalyzeMethod() {
        // 테스트할 로그 라인을 준비
        String logLine = "[200][http://apis.daum.net/search/image?apikey=tr8j&q=daum][IE][2012-06-10 10:10:57]\n" +
                "[200][http://apis.daum.net/search/image?apikey=abc123&q=test][Chrome][2012-06-10 10:12:30]\n";
        // 준비한 로그 라인으로 스트림 생성
        Stream<String> logLinesStream = Stream.of(logLine);

        // LogAnalysisService 객체 생성
        LogAnalysisService logAnalysisService = new LogAnalysisService();

        // analyze 메소드 호출
        LogAnalysisOutputDto outputDto = logAnalysisService.analyze(logLinesStream);

        // 결과를 검증
        assertEquals(1, outputDto.getApikeyCounts().get("tr8j"));
        assertEquals(2, outputDto.getApiServiceIdCounts().get("image"));
        assertEquals(1, outputDto.getBrowserCounts().get("IE"));

        // LogAnalysisService 객체 생성
        LogPrinterService logPrinterService = new LogPrinterService();

        logPrinterService.printResults(outputDto);

    }
}