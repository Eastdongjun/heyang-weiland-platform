package com.hwsmp.alarm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hwsmp.alarm.model.dto.AlarmConfirmDTO;
import com.hwsmp.alarm.model.dto.AlarmQueryDTO;
import com.hwsmp.alarm.model.entity.Alarm;
import com.hwsmp.alarm.model.vo.AlarmVO;

import java.util.Map;

public interface AlarmService extends IService<Alarm> {
    Page<AlarmVO> queryPage(AlarmQueryDTO query);
    AlarmVO getDetail(String id);
    void confirm(AlarmConfirmDTO dto);
    void markFalse(String id);
    Map<String, Object> statistics();
}
