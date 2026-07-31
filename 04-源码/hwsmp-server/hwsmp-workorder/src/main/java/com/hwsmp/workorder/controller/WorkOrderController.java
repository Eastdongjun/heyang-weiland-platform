package com.hwsmp.workorder.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hwsmp.common.Result;
import com.hwsmp.workorder.model.dto.*;
import com.hwsmp.workorder.model.vo.WorkOrderVO;
import com.hwsmp.workorder.service.WorkOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "工单管理", description = "工单CRUD、派发、审核、统计")
@RestController
@RequestMapping("/api/v1/work-orders")
@RequiredArgsConstructor
public class WorkOrderController {

    private final WorkOrderService workOrderService;

    @Operation(summary = "分页查询")
    @GetMapping
    public Result<Page<WorkOrderVO>> list(WorkOrderQueryDTO query) {
        return Result.ok(workOrderService.queryPage(query));
    }

    @Operation(summary = "详情")
    @GetMapping("/{id}")
    public Result<WorkOrderVO> detail(@PathVariable String id) {
        return Result.ok(workOrderService.getDetail(id));
    }

    @Operation(summary = "创建工单")
    @PostMapping
    public Result<String> create(@Valid @RequestBody WorkOrderDTO dto) {
        return Result.ok(workOrderService.create(dto));
    }

    @Operation(summary = "派发工单")
    @PostMapping("/{id}/dispatch")
    public Result<Void> dispatch(@PathVariable String id, @RequestParam Long assigneeId) {
        workOrderService.dispatch(id, assigneeId);
        return Result.ok();
    }

    @Operation(summary = "接单")
    @PostMapping("/{id}/accept")
    public Result<Void> accept(@PathVariable String id) {
        workOrderService.accept(id);
        return Result.ok();
    }

    @Operation(summary = "处理提交")
    @PostMapping("/process")
    public Result<Void> process(@Valid @RequestBody WorkOrderProcessDTO dto) {
        workOrderService.process(dto);
        return Result.ok();
    }

    @Operation(summary = "审核")
    @PostMapping("/{id}/audit")
    public Result<Void> audit(@PathVariable String id,
                              @RequestParam String opinion,
                              @RequestParam(defaultValue = "true") boolean passed) {
        workOrderService.audit(id, opinion, passed);
        return Result.ok();
    }

    @Operation(summary = "工单统计")
    @GetMapping("/statistics")
    public Result<Map<String, Object>> statistics() {
        return Result.ok(workOrderService.statistics());
    }
}
