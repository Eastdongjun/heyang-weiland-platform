package com.hwsmp.alarm.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AlarmConfirmDTO {
    @NotBlank(message = "告警ID不能为空")
    private String alarmId;
    @NotBlank(message = "处置结果不能为空")
    private String confirmRemark;
}
