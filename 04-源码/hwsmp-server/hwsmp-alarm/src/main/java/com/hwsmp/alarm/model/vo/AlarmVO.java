package com.hwsmp.alarm.model.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class AlarmVO {
    private String id;
    private String alarmSource;
    private String alarmType;
    private Integer alarmLevel;
    private String alarmLevelName;
    private String alarmStatus;
    private String alarmStatusName;
    private Long deviceId;
    private String deviceName;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String locationText;
    private Double confidence;
    private List<String> snapshots;
    private String videoClipUrl;
    private String weatherInfo;
    private String description;
    private LocalDateTime occurredAt;
    private LocalDateTime confirmedAt;
    private String confirmRemark;
    private String workOrderId;
}
