package com.hwsmp.alarm.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.hwsmp.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_alarm", autoResultMap = true)
public class Alarm extends BaseEntity {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String alarmSource;
    private String alarmType;
    private Integer alarmLevel;
    private String alarmStatus;
    private Long deviceId;
    private String deviceName;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String locationText;
    private Double confidence;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> snapshots;
    private String videoClipUrl;
    private String weatherInfo;
    private String description;
    private LocalDateTime occurredAt;
    private LocalDateTime confirmedAt;
    private Long confirmedBy;
    private String confirmRemark;
    private String workOrderId;
    private String extInfo;
}
