package com.hwsmp.device.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hwsmp.common.constant.SysConstants;
import com.hwsmp.common.exception.BizException;
import com.hwsmp.device.mapper.DeviceMapper;
import com.hwsmp.device.model.dto.DeviceDTO;
import com.hwsmp.device.model.dto.DeviceQueryDTO;
import com.hwsmp.device.model.entity.Device;
import com.hwsmp.device.model.vo.DeviceVO;
import com.hwsmp.device.service.DeviceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {

    private static final Logger log = LoggerFactory.getLogger(DeviceServiceImpl.class);

    private static final String[] DEVICE_TYPE_NAMES = {"", "激光云台", "摄像头", "传感器", "无人机机场", "球机"};

    @Override
    public Page<DeviceVO> queryPage(DeviceQueryDTO query) {
        LambdaQueryWrapper<Device> wrapper = new LambdaQueryWrapper<Device>()
                .like(StrUtil.isNotBlank(query.getKeyword()), Device::getDeviceName, query.getKeyword())
                .eq(query.getDeviceType() != null, Device::getDeviceType, query.getDeviceType())
                .eq(query.getRegionId() != null, Device::getRegionId, query.getRegionId())
                .eq(query.getOnlineStatus() != null, Device::getOnlineStatus, query.getOnlineStatus())
                .eq(query.getStatus() != null, Device::getStatus, query.getStatus())
                .orderByDesc(Device::getCreatedAt);

        Page<Device> page = page(new Page<>(query.getPage(), query.getSize()), wrapper);
        List<DeviceVO> records = page.getRecords().stream()
                .map(this::toVO)
                .collect(Collectors.toList());

        Page<DeviceVO> result = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        result.setRecords(records);
        return result;
    }

    @Override
    public DeviceVO getDetail(Long id) {
        Device device = getById(id);
        if (device == null) {
            throw new BizException("设备不存在");
        }
        return toVO(device);
    }

    @Override
    public void create(DeviceDTO dto) {
        // 校验设备编码唯一性
        if (lambdaQuery().eq(Device::getDeviceCode, dto.getDeviceCode()).count() > 0) {
            throw new BizException("设备编码已存在: " + dto.getDeviceCode());
        }
        Device device = new Device();
        BeanUtil.copyProperties(dto, device);
        device.setOnlineStatus(0);
        device.setMonthlyFlowUsed(0);
        device.setStatus(1);
        if (dto.getMaintenanceCycle() != null) {
            device.setNextMaintenanceDate(LocalDate.now().plusDays(dto.getMaintenanceCycle()));
        }
        save(device);
        log.info("设备创建成功: {} ({})", device.getDeviceName(), device.getDeviceCode());
    }

    @Override
    public void update(DeviceDTO dto) {
        Device device = getById(dto.getId());
        if (device == null) {
            throw new BizException("设备不存在");
        }
        BeanUtil.copyProperties(dto, device, "id", "deviceCode");
        updateById(device);
    }

    @Override
    public void remove(Long id) {
        removeById(id);
    }

    @Override
    public List<DeviceVO> treeByRegion(Long regionId) {
        return lambdaQuery()
                .eq(regionId != null, Device::getRegionId, regionId)
                .eq(Device::getStatus, 1)
                .list()
                .stream()
                .map(this::toVO)
                .collect(Collectors.toList());
    }

    private DeviceVO toVO(Device device) {
        DeviceVO vo = new DeviceVO();
        BeanUtil.copyProperties(device, vo);
        int type = device.getDeviceType() != null ? device.getDeviceType() : 0;
        if (type > 0 && type < DEVICE_TYPE_NAMES.length) {
            vo.setDeviceTypeName(DEVICE_TYPE_NAMES[type]);
        }
        return vo;
    }
}
