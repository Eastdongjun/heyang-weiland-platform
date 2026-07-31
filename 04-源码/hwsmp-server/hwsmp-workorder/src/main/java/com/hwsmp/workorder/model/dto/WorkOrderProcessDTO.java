package com.hwsmp.workorder.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WorkOrderProcessDTO {
    @NotBlank
    private String id;
    private String processResult;
    private String processImages;
}
