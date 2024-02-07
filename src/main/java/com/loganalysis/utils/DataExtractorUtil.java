package com.loganalysis.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 데이터 추출을 위한 util class
 */
public class DataExtractorUtil {
    //입력된 pattern을 구분자로 추출해서 list로 반환
    public static List<String> extractData(String input, String pattern) {
        List<String> extractedData = new ArrayList<>();
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input);

        while (matcher.find()) {
            extractedData.add(matcher.group(1).trim());
        }

        return extractedData;
    }
}
