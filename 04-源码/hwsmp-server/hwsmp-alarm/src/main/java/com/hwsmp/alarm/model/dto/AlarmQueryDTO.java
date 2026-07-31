package com.hwsmp.alarm.model.dto;

import lombok.Data;

@Data
public class AlarmQueryDTO {
    private String keyword;
    private Integer alarmLevel;
    private String alarmStatus;
    private String alarmType;
    private String startTime;
    private String endTime;
    private Integer page = 1;
    private Integer size = 10;
}
