package com.hwsmp.device.model.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DeviceVO {
    private Long id;
    private String deviceName;
    private String deviceCode;
    private Integer deviceType;
    private String deviceTypeName;
    private String deviceModel;
    private String gbDeviceId;
    private String rtspMainUrl;
    private String rtspSubUrl;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String locationText;
    private Long regionId;
    private String regionName;
    private String picUrl;
    private String deviceIp;
    private Integer onlineStatus;
    private String simCardNo;
    private Integer monthlyFlowLimit;
    private Integer monthlyFlowUsed;
    private LocalDate nextMaintenanceDate;
    private Integer maintenanceCycle;
    private LocalDate installDate;
    private String remark;
    private Integer status;
    private LocalDateTime createdAt;
}
