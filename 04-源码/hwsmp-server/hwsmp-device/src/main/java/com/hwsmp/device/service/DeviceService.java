package com.hwsmp.device.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hwsmp.device.model.dto.DeviceDTO;
import com.hwsmp.device.model.dto.DeviceQueryDTO;
import com.hwsmp.device.model.entity.Device;
import com.hwsmp.device.model.vo.DeviceVO;

import java.util.List;

public interface DeviceService extends IService<Device> {
    Page<DeviceVO> queryPage(DeviceQueryDTO query);
    DeviceVO getDetail(Long id);
    void create(DeviceDTO dto);
    void update(DeviceDTO dto);
    void remove(Long id);
    List<DeviceVO> treeByRegion(Long regionId);
}
