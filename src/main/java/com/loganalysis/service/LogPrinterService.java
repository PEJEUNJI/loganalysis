package com.loganalysis.service;

import com.loganalysis.service.dto.LogAnalysisOutputDto;
import com.loganalysis.utils.FileWriterUtil;

import java.util.Map;
import java.util.stream.Collectors;
/**
 * 결과 출력을 위한 LogResultsPrinter 클래스
 */
public class LogPrinterService {
    public void printResults(LogAnalysisOutputDto outputDto) {
        // 가장 많이 호출된 apikey 찾기
        String mostCalledApikey = outputDto.getApikeyCounts().entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
        // 상위 3개 ApiServiceId 리스트 구하기
        Map<String, Integer> topApiServiceIdsValues = outputDto.getApiServiceIdCounts().entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // 브라우저 종류별 비율 계산
        Map<String, Double> browserRatios = outputDto.getBrowserCounts().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> (double) entry.getValue() / outputDto.getApikeyCounts().values().stream().mapToInt(Integer::intValue).sum()
                ));

        // 결과 출력
        LogAnalysisOutputDto  writerDto = new LogAnalysisOutputDto(mostCalledApikey, topApiServiceIdsValues, browserRatios);

        // 파일 출력
        FileWriterUtil.writeToFile(writerDto);
    }
}
