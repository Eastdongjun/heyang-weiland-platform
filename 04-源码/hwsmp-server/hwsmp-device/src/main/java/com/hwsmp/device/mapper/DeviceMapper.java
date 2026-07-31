package com.hwsmp.device.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwsmp.device.model.entity.Device;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeviceMapper extends BaseMapper<Device> {
}
