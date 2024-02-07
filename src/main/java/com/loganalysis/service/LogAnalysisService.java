package com.loganalysis.service;
import com.loganalysis.service.dto.LogAnalysisOutputDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import com.loganalysis.utils.DataExtractorUtil;

public class LogAnalysisService {
    /**
     * LOG FORMAT
     * [200][http://apis.daum.net/search/image?apikey=tr8j&q=daum][IE][2012-06-10 10:10:57]
     * 1. 가장 많이 호출된 apikey
     * 2. 상위 top3 apiServiceId 구하기
     * 3. 브라우저별 통계 구하기
     */

    public LogAnalysisOutputDto analyze(Stream<String> lines) {
        LogAnalysisOutputDto resultDTO = new LogAnalysisOutputDto();

        // 각 라인을 분석하여 정보 추출
        lines.flatMap(line -> Arrays.stream(line.split("\n"))).forEach(line -> {
            //[]안의 문자열을 추출
            String pattern = "\\[(.*?)\\]";
            List<String> extractedData = DataExtractorUtil.extractData((String)line, pattern);

            // tokens[0]이 [200] 형태인 경우에만 분석 수행
            if (extractedData.size() >= 4 && extractedData.get(0).trim().equals("200")) {

                // "apikey=" 다음에 나오는 '&' 문자가 나타날 때까지의 모든 문자열을 추출
                String apikey = DataExtractorUtil.extractData(extractedData.get(1), "apikey=([^&]+)").stream().findFirst().orElse("");
                // "search/" 다음에 나오는 '?' 문자가 나타날 때까지의 모든 문자열을 추출
                String apiServiceId = DataExtractorUtil.extractData(extractedData.get(1), "search/([^?]+)").stream().findFirst().orElse("");
                String browser = extractedData.get(2);

                // apikey별 호출 횟수 카운트
                resultDTO.getApikeyCounts().put(apikey, resultDTO.getApikeyCounts().getOrDefault(apikey, 0) + 1);

                // 브라우저 종류별 호출 횟수 카운트
                resultDTO.getBrowserCounts().put(browser, resultDTO.getBrowserCounts().getOrDefault(browser, 0) + 1);

                // api service id별 호출 횟수 카운트
                resultDTO.getApiServiceIdCounts().put(apiServiceId, resultDTO.getApiServiceIdCounts().getOrDefault(apiServiceId, 0) + 1);
            }
        });
        return resultDTO;
    }
}