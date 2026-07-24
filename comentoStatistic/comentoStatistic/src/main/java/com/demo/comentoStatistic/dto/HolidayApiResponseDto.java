package com.demo.comentoStatistic.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * 한국천문연구원_특일 정보 API(공공데이터포털)가 응답으로 주는
 * JSON 데이터를 자바 객체로 옮겨 담기 위한 DTO.
 * API 응답구조가 중첩의 형태로 되어 있어 중첩클래스로 설계
 * 예시 응답:
 * {
 *   "response": {
 *     "body": {
 *       "items": {
 *         "item": [
 *           { "dateName": "광복절", "isHoliday": "Y", "locdate": 20240815 }
 *         ]
 *       }
 *     }
 *   }
 * }
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true) // 정의 되어지지 않은 응답이 추가로 와도 무시
public class HolidayApiResponseDto
{
    private Response response;

    /** API 응답의 최상위 "response" 부분을 담는 클래스 */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Response
    {
        private Body body;
    }

    /** "response" 안의 "body" 부분을 담는 클래스 */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Body
    {
        private Items Items;
    }

    /** "body" 안의 "items" 부분을 담는 클래스 (실제 공휴일 목록을 감싸고 있음) */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Items {
        private List<Item> item;  // 실제 공휴일 데이터 목록
    }

    /**
     * 공휴일 데이터 한 건(한 날짜)을 담는 클래스.
     * - dateName: 공휴일 이름 (예: "광복절")
     * - isHoliday: 공공기관 휴일 여부 ("Y" or "N")
     * - locdate: 날짜 (예: "20240815")
     */
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private String dateName;
        private String isHoliday;
        private String locdate;
    }
}
