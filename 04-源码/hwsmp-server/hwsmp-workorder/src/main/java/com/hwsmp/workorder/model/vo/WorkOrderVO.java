package com.hwsmp.workorder.model.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class WorkOrderVO {
    private String id;
    private String orderNo;
    private String title;
    private Integer priority;
    private String priorityName;
    private Integer orderStatus;
    private String orderStatusName;
    private String alarmId;
    private String content;
    private String images;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String locationText;
    private Long assigneeId;
    private String assigneeName;
    private String processResult;
    private String processImages;
    private String auditOpinion;
    private String assignReason;
    private LocalDateTime acceptedAt;
    private LocalDateTime completedAt;
    private LocalDateTime createdAt;
}
