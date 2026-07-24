package com.demo.comentoStatistic.service;

import com.demo.comentoStatistic.dto.HolidayApiResponseDto;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class HolidayApiService {

    private final RestTemplate restTemplate;

    @Value("${holiday.api.service-key}")
    private String serviceKey;

    @Value("${holiday.api.url}")
    private String apiUrl;

    public HolidayApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<String> getHolidayDates(String year, String month) {
        List<String> holidayDates = new ArrayList<>();

        try {
            String url = UriComponentsBuilder.fromUriString(apiUrl)
                    .queryParam("serviceKey", serviceKey)
                    .queryParam("solYear", "20" + year)
                    .queryParam("solMonth", month)
                    .queryParam("_type", "json")
                    .queryParam("numOfRows", 50)
                    .build(true)
                    .toUriString();

            System.out.println("[HolidayApiService] 요청 URL: " + url);

            String response = restTemplate.getForObject(url, String.class);

            System.out.println("[HolidayApiService] 응답: " + response);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            JsonNode itemNode = root
                    .path("response")
                    .path("body")
                    .path("items")
                    .path("item");

            // item이 배열이면 그대로 순회, 객체 하나면 리스트처럼 감싸서 처리
            if (itemNode.isArray()) {
                for (JsonNode node : itemNode) {
                    addIfHoliday(holidayDates, node);
                }
            } else if (itemNode.isObject()) {
                addIfHoliday(holidayDates, itemNode);
            }

        } catch (Exception e) {
            System.err.println("[HolidayApiService] 공휴일 API 호출 실패: " + e.getMessage());
            e.printStackTrace();
        }

        return holidayDates;
    }

    private void addIfHoliday(List<String> holidayDates, JsonNode item) {
        String isHoliday = item.path("isHoliday").asText();
        if ("Y".equals(isHoliday)) {
            String locdate = item.path("locdate").asText();   // "20240815"
            if (locdate.length() >= 8) {
                holidayDates.add(locdate.substring(2));         // "240815"
            }
        }
    }
}