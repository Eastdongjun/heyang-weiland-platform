package com.hwsmp.device.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hwsmp.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 设备实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_device")
public class Device extends BaseEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String deviceName;
    private String deviceCode;
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
    private Integer onlineStatus;
    private String simCardNo;
    private Integer monthlyFlowLimit;
    private Integer monthlyFlowUsed;
    private LocalDate nextMaintenanceDate;
    private Integer maintenanceCycle;
    private LocalDate installDate;
    private String remark;
    private Integer status;
}
