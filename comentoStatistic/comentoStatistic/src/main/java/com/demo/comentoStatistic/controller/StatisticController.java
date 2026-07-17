package com.demo.comentoStatistic.controller;

import com.demo.comentoStatistic.dto.DailyLoginCountDto;
import com.demo.comentoStatistic.dto.DailyMeanCountDto;
import com.demo.comentoStatistic.dto.YearCountDto;
import com.demo.comentoStatistic.dto.YearMonthCountDto;
import com.demo.comentoStatistic.service.StatisticService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StatisticController
{
    @Autowired
    StatisticService statisticService;

    // 년도에 따른 출력
    @RequestMapping(value="/api/v1/logins/{year}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<YearCountDto> getYearLoginCount(@PathVariable("year") String year)
    { return ResponseEntity.ok(statisticService.getYearLogins(year)); }

    // 년도, 월에 따른 출력
    @RequestMapping(value="/api/v1/logins/{year}/{month}", produces = "application/json")
    @ResponseBody
    public  ResponseEntity<YearMonthCountDto> getYearMonthLoginCount(@PathVariable("year") String year, @PathVariable("month") String month)
    { return ResponseEntity.ok(statisticService.getYearMonthLogins(year, month)); }

    // 년도, 월, 일자에 따른 출력
    @RequestMapping(value="/api/v1/logins/{year}/{month}/{day}", produces = "application/json")
    @ResponseBody
    public  ResponseEntity<DailyLoginCountDto> getYearMonthLoginCount(@PathVariable("year") String year, @PathVariable("month") String month, @PathVariable("day") String day)
    { return ResponseEntity.ok(statisticService.getYearMonthDayLogins(year, month, day)); }


    // 1. 월에 따른 출력
    @RequestMapping(value = "/api/v1/stats/monthly", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<YearMonthCountDto>> getMonthlyLoginStats()
    { return ResponseEntity.ok(statisticService.getMonthlyLoginStats()); }

    // 2. 일에 따른 출력
    @RequestMapping(value = "/api/v1/stats/daily", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<DailyLoginCountDto>> getDailyLoginStats()
    { return ResponseEntity.ok(statisticService.getDailyLoginStats()); }

    // 3. 하루 평균 로그인 수 출력
    @RequestMapping(value = "/mean", produces = "application/json")
    @ResponseBody
    public ResponseEntity<DailyMeanCountDto> getDailyMeanCount()
    { return  ResponseEntity.ok(statisticService.getDailyMeanLoginCount()); }

}
