package com.hwsmp.device.model.dto;

import lombok.Data;

@Data
public class DeviceQueryDTO {
    private String keyword;
    private Integer deviceType;
    private Long regionId;
    private Integer onlineStatus;
    private Integer status;
    private Integer page = 1;
    private Integer size = 10;
}
