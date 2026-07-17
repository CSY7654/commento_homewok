package com.demo.comentoStatistic.service;

import com.demo.comentoStatistic.dao.StatisticMapper;
import com.demo.comentoStatistic.dto.DailyLoginCountDto;
import com.demo.comentoStatistic.dto.DailyMeanCountDto;
import com.demo.comentoStatistic.dto.YearCountDto;
import com.demo.comentoStatistic.dto.YearMonthCountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService
{
    @Autowired
    StatisticMapper statisticMapper;
    // 년도
    public YearCountDto getYearLogins(String year)
    { return statisticMapper.selectYearLogin(year); }
    // 년도, 월
    public YearMonthCountDto getYearMonthLogins(String year, String month)
    { return statisticMapper.selectYearMonthLogin(year + month); }
    // 년도 월 일
    public DailyLoginCountDto getYearMonthDayLogins(String year, String month, String day)
    { return statisticMapper.selectYearMonthDayLogin(year + month + day); }

    // 1. 월별
    public List<YearMonthCountDto> getMonthlyLoginStats()
    { return statisticMapper.selectMonthlyLoginStats(); }
    //2. 일별
    public List<DailyLoginCountDto> getDailyLoginStats()
    { return statisticMapper.selectDailyLoginStats(); }
    // 3. 평균
    public DailyMeanCountDto getDailyMeanLoginCount()
    { return statisticMapper.selectDailyMeanLoginCount(); }

}
