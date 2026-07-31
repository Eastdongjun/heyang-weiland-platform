package com.hwsmp.alarm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hwsmp.common.Result;
import com.hwsmp.alarm.model.dto.AlarmConfirmDTO;
import com.hwsmp.alarm.model.dto.AlarmQueryDTO;
import com.hwsmp.alarm.model.vo.AlarmVO;
import com.hwsmp.alarm.service.AlarmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "智能告警", description = "告警列表、确认、误报、统计")
@RestController
@RequestMapping("/api/v1/alarms")
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;

    @Operation(summary = "告警列表")
    @GetMapping
    public Result<Page<AlarmVO>> list(AlarmQueryDTO query) {
        return Result.ok(alarmService.queryPage(query));
    }

    @Operation(summary = "告警详情")
    @GetMapping("/{id}")
    public Result<AlarmVO> detail(@PathVariable String id) {
        return Result.ok(alarmService.getDetail(id));
    }

    @Operation(summary = "确认告警")
    @PostMapping("/confirm")
    public Result<Void> confirm(@Valid @RequestBody AlarmConfirmDTO dto) {
        alarmService.confirm(dto);
        return Result.ok();
    }

    @Operation(summary = "标记误报")
    @PostMapping("/{id}/false-alarm")
    public Result<Void> markFalse(@PathVariable String id) {
        alarmService.markFalse(id);
        return Result.ok();
    }

    @Operation(summary = "告警统计")
    @GetMapping("/statistics")
    public Result<Map<String, Object>> statistics() {
        return Result.ok(alarmService.statistics());
    }
}
