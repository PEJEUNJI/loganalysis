package com.loganalysis.service.dto;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LogAnalysisOutputDto {
    // apiKey 종류별 호출 횟수를 저장하는 map
    private Map<String, Integer> apikeyCounts;
    // 브라우저 종류별 호출 횟수를 저하는 map
    private Map<String, Integer> browserCounts;
    // apiServiceId 호출 횟수를 저장하는 map
    private Map<String, Integer> apiServiceIdCounts;
    //가장 많이 호출된 apiKey
    private String mostCalledApikey;
    // 상위 apiserviceid 리스트와 그에 대응하는 값들을 저장하는 map
    private Map<String, Integer> topApiServiceIdsValues;
    //브라우저별 비율
    private Map<String, Double> browserRatios;
    // 기본 생성자
    public LogAnalysisOutputDto() {
        this.apikeyCounts = new HashMap<>();
        this.browserCounts = new HashMap<>();
        this.apiServiceIdCounts = new HashMap<>();
    }
    //파일 출력을 위한 dto
    public LogAnalysisOutputDto(String mostCalledApikey, Map<String, Integer> topApiServiceIdsValues, Map<String, Double> browserRatios) {
        this.mostCalledApikey = mostCalledApikey;
        this.topApiServiceIdsValues = topApiServiceIdsValues;
        this.browserRatios = browserRatios;
    }
}