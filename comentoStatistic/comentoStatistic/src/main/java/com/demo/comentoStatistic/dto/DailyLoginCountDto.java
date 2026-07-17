package com.demo.comentoStatistic.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailyLoginCountDto
{
    String yearMonthDay;
    Integer totCnt;
}
