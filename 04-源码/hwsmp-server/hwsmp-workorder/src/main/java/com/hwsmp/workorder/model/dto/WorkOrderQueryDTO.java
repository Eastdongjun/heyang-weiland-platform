package com.hwsmp.workorder.model.dto;

import lombok.Data;

@Data
public class WorkOrderQueryDTO {
    private String keyword;
    private Integer orderStatus;
    private Long assigneeId;
    private String startTime;
    private String endTime;
    private Integer page = 1;
    private Integer size = 10;
}
