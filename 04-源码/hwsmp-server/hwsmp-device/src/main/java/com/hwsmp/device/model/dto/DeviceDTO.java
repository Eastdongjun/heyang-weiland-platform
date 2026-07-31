package com.hwsmp.device.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class DeviceDTO {
    private Long id;
    @NotBlank(message = "设备名称不能为空")
    private String deviceName;
    @NotBlank(message = "设备编码不能为空")
    private String deviceCode;
    @NotNull(message = "设备类型不能为空")
    private Integer deviceType;
    private String deviceModel;
    private String gbDeviceId;
    private String rtspMainUrl;
    private String rtspSubUrl;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String locationText;
    private Long regionId;
    private String picUrl;
    private String deviceIp;
    private String simCardNo;
    private Integer monthlyFlowLimit;
    private Integer maintenanceCycle;
    private String remark;
}
