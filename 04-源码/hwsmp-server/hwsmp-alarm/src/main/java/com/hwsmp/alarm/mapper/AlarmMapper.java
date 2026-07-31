package com.hwsmp.alarm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwsmp.alarm.model.entity.Alarm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlarmMapper extends BaseMapper<Alarm> {
}
