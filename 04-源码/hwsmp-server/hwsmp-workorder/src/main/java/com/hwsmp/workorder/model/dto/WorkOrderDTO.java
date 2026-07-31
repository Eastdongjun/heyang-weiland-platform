package com.hwsmp.workorder.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class WorkOrderDTO {
    private String id;
    @NotBlank(message = "标题不能为空")
    private String title;
    private Integer priority;
    private String content;
    private String images;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String locationText;
    private Long assigneeId;
}
