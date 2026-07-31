package com.hwsmp.alarm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hwsmp.common.constant.SysConstants;
import com.hwsmp.common.exception.BizException;
import com.hwsmp.alarm.mapper.AlarmMapper;
import com.hwsmp.alarm.model.dto.AlarmConfirmDTO;
import com.hwsmp.alarm.model.dto.AlarmQueryDTO;
import com.hwsmp.alarm.model.entity.Alarm;
import com.hwsmp.alarm.model.vo.AlarmVO;
import com.hwsmp.alarm.service.AlarmService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class AlarmServiceImpl extends ServiceImpl<AlarmMapper, Alarm> implements AlarmService {

    private static final Logger log = LoggerFactory.getLogger(AlarmServiceImpl.class);
    private static final String[] LEVEL_NAMES = {"", "一级火警", "二级疑似", "三级环境", "四级提示"};

    @Override
    public Page<AlarmVO> queryPage(AlarmQueryDTO query) {
        LambdaQueryWrapper<Alarm> wrapper = new LambdaQueryWrapper<Alarm>()
                .like(StrUtil.isNotBlank(query.getKeyword()), Alarm::getLocationText, query.getKeyword())
                .eq(query.getAlarmLevel() != null, Alarm::getAlarmLevel, query.getAlarmLevel())
                .eq(StrUtil.isNotBlank(query.getAlarmStatus()), Alarm::getAlarmStatus, query.getAlarmStatus())
                .eq(StrUtil.isNotBlank(query.getAlarmType()), Alarm::getAlarmType, query.getAlarmType())
                .ge(StrUtil.isNotBlank(query.getStartTime()), Alarm::getOccurredAt, query.getStartTime())
                .le(StrUtil.isNotBlank(query.getEndTime()), Alarm::getOccurredAt, query.getEndTime())
                .orderByDesc(Alarm::getOccurredAt);

        Page<Alarm> page = page(new Page<>(query.getPage(), query.getSize()), wrapper);
        List<AlarmVO> records = page.getRecords().stream().map(this::toVO).toList();

        Page<AlarmVO> result = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        result.setRecords(records);
        return result;
    }

    @Override
    public AlarmVO getDetail(String id) {
        Alarm alarm = getById(id);
        if (alarm == null) throw new BizException("告警不存在");
        return toVO(alarm);
    }

    @Override
    public void confirm(AlarmConfirmDTO dto) {
        Alarm alarm = getById(dto.getAlarmId());
        if (alarm == null) throw new BizException("告警不存在");
        alarm.setAlarmStatus("confirmed");
        alarm.setConfirmedAt(LocalDateTime.now());
        alarm.setConfirmRemark(dto.getConfirmRemark());
        updateById(alarm);
        log.info("告警确认: {} -> {}", alarm.getId(), dto.getConfirmRemark());
    }

    @Override
    public void markFalse(String id) {
        Alarm alarm = getById(id);
        if (alarm == null) throw new BizException("告警不存在");
        alarm.setAlarmStatus("false_alarm");
        alarm.setConfirmedAt(LocalDateTime.now());
        updateById(alarm);
    }

    @Override
    public Map<String, Object> statistics() {
        Map<String, Object> stats = new HashMap<>();
        for (int level = 1; level <= 4; level++) {
            stats.put("level" + level, lambdaQuery().eq(Alarm::getAlarmLevel, level).count());
        }
        stats.put("unconfirmed", lambdaQuery().eq(Alarm::getAlarmStatus, "pending").count());
        stats.put("today", lambdaQuery().ge(Alarm::getOccurredAt, LocalDateTime.now().toLocalDate().atStartOfDay()).count());
        return stats;
    }

    private AlarmVO toVO(Alarm alarm) {
        AlarmVO vo = new AlarmVO();
        BeanUtil.copyProperties(alarm, vo);
        int level = alarm.getAlarmLevel() != null ? alarm.getAlarmLevel() : 0;
        if (level > 0 && level < LEVEL_NAMES.length) vo.setAlarmLevelName(LEVEL_NAMES[level]);
        vo.setAlarmStatusName(switch (alarm.getAlarmStatus()) {
            case "pending" -> "待确认";
            case "confirmed" -> "已确认";
            case "false_alarm" -> "误报";
            default -> alarm.getAlarmStatus();
        });
        return vo;
    }
}
