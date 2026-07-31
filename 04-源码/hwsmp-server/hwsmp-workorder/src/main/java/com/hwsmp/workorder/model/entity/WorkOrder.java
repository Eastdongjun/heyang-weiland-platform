package com.hwsmp.workorder.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hwsmp.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_work_order")
public class WorkOrder extends BaseEntity {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String orderNo;
    private String title;
    private Integer priority;
    private Integer orderStatus;
    private String alarmId;
    private String content;
    private String images;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String locationText;
    private Long assigneeId;
    private String assigneeName;
    private String assignReason;
    private LocalDateTime acceptedAt;
    private String processResult;
    private String processImages;
    private String auditOpinion;
    private Long auditorId;
    private LocalDateTime auditedAt;
    private LocalDateTime completedAt;
}
