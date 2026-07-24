package com.demo.comentoStatistic.dao;


import com.demo.comentoStatistic.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface StatisticMapper {

    // 특정 년도에 따른 접속자 수 출력
    YearCountDto selectYearLogin(String year);
    // 특정 년도와 월에 따른 접속자 수 출력
    YearMonthCountDto selectYearMonthLogin(String yearMonth);
    // 특정 년도와 월, 일자에 따른 접속자 수 출력
    DailyLoginCountDto selectYearMonthDayLogin(String yearMonthDay);

    // 1. 모든 년도 특정 월에 따른 접속자 수 출력
    List<YearMonthCountDto> selectMonthlyLoginStats();
    // 2. 모든 년도 특정
    List<DailyLoginCountDto> selectDailyLoginStats();
    // 3. 평균 접속자 수 출력
    DailyMeanCountDto selectDailyMeanLoginCount();
    // 4) 휴일 제외 로그인 수
    List<DayTypeCountDto> selectLoginCountByDayType(Map<String, Object> params);


}
