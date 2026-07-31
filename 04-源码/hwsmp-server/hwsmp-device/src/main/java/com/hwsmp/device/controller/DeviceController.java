package com.hwsmp.device.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hwsmp.common.Result;
import com.hwsmp.device.model.dto.DeviceDTO;
import com.hwsmp.device.model.dto.DeviceQueryDTO;
import com.hwsmp.device.model.vo.DeviceVO;
import com.hwsmp.device.service.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "设备管理", description = "设备台账、在线状态、设备树")
@RestController
@RequestMapping("/api/v1/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @Operation(summary = "分页查询设备列表")
    @GetMapping
    public Result<Page<DeviceVO>> list(DeviceQueryDTO query) {
        return Result.ok(deviceService.queryPage(query));
    }

    @Operation(summary = "设备详情")
    @GetMapping("/{id}")
    public Result<DeviceVO> detail(@PathVariable Long id) {
        return Result.ok(deviceService.getDetail(id));
    }

    @Operation(summary = "新增设备")
    @PostMapping
    public Result<Void> create(@Valid @RequestBody DeviceDTO dto) {
        deviceService.create(dto);
        return Result.ok();
    }

    @Operation(summary = "更新设备")
    @PutMapping
    public Result<Void> update(@Valid @RequestBody DeviceDTO dto) {
        deviceService.update(dto);
        return Result.ok();
    }

    @Operation(summary = "删除设备")
    @DeleteMapping("/{id}")
    public Result<Void> remove(@PathVariable Long id) {
        deviceService.remove(id);
        return Result.ok();
    }

    @Operation(summary = "设备树 (按区域分组)")
    @GetMapping("/tree")
    public Result<List<DeviceVO>> tree(@RequestParam(required = false) Long regionId) {
        return Result.ok(deviceService.treeByRegion(regionId));
    }
}
