package com.hwsmp.workorder.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hwsmp.common.constant.SysConstants;
import com.hwsmp.common.exception.BizException;
import com.hwsmp.workorder.mapper.WorkOrderMapper;
import com.hwsmp.workorder.model.dto.*;
import com.hwsmp.workorder.model.entity.WorkOrder;
import com.hwsmp.workorder.model.vo.WorkOrderVO;
import com.hwsmp.workorder.service.WorkOrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class WorkOrderServiceImpl extends ServiceImpl<WorkOrderMapper, WorkOrder> implements WorkOrderService {

    private static final Logger log = LoggerFactory.getLogger(WorkOrderServiceImpl.class);
    private static final String[] STATUS_NAMES = {"待派发", "待接单", "处理中", "待审核", "已完成", "已归档"};
    private static final String[] PRIORITY_NAMES = {"", "紧急", "高", "中", "低"};

    @Override
    public Page<WorkOrderVO> queryPage(WorkOrderQueryDTO query) {
        LambdaQueryWrapper<WorkOrder> wrapper = new LambdaQueryWrapper<WorkOrder>()
                .like(StrUtil.isNotBlank(query.getKeyword()), WorkOrder::getTitle, query.getKeyword())
                .eq(query.getOrderStatus() != null, WorkOrder::getOrderStatus, query.getOrderStatus())
                .eq(query.getAssigneeId() != null, WorkOrder::getAssigneeId, query.getAssigneeId())
                .ge(StrUtil.isNotBlank(query.getStartTime()), WorkOrder::getCreatedAt, query.getStartTime())
                .le(StrUtil.isNotBlank(query.getEndTime()), WorkOrder::getCreatedAt, query.getEndTime())
                .orderByDesc(WorkOrder::getCreatedAt);

        Page<WorkOrder> page = page(new Page<>(query.getPage(), query.getSize()), wrapper);
        List<WorkOrderVO> records = page.getRecords().stream().map(this::toVO).toList();

        Page<WorkOrderVO> result = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        result.setRecords(records);
        return result;
    }

    @Override
    public WorkOrderVO getDetail(String id) {
        WorkOrder wo = getById(id);
        if (wo == null) throw new BizException("工单不存在");
        return toVO(wo);
    }

    @Override
    public String create(WorkOrderDTO dto) {
        WorkOrder wo = new WorkOrder();
        BeanUtil.copyProperties(dto, wo);
        wo.setOrderNo(generateOrderNo());
        wo.setOrderStatus(SysConstants.WO_PENDING_ASSIGN);
        if (dto.getPriority() == null) wo.setPriority(3); // 默认中优先级
        save(wo);
        log.info("工单创建: {} - {}", wo.getOrderNo(), wo.getTitle());
        return wo.getId();
    }

    @Override
    public void dispatch(String id, Long assigneeId) {
        WorkOrder wo = getById(id);
        if (wo == null) throw new BizException("工单不存在");
        if (wo.getOrderStatus() != SysConstants.WO_PENDING_ASSIGN) throw new BizException("当前状态不可派发");
        wo.setOrderStatus(SysConstants.WO_PENDING_ACCEPT);
        wo.setAssigneeId(assigneeId);
        updateById(wo);
    }

    @Override
    public void accept(String id) {
        WorkOrder wo = getById(id);
        if (wo == null) throw new BizException("工单不存在");
        if (wo.getOrderStatus() != SysConstants.WO_PENDING_ACCEPT) throw new BizException("当前状态不可接单");
        wo.setOrderStatus(SysConstants.WO_PROCESSING);
        wo.setAcceptedAt(LocalDateTime.now());
        updateById(wo);
    }

    @Override
    public void process(WorkOrderProcessDTO dto) {
        WorkOrder wo = getById(dto.getId());
        if (wo == null) throw new BizException("工单不存在");
        if (wo.getOrderStatus() != SysConstants.WO_PROCESSING) throw new BizException("当前状态不可处理");
        wo.setOrderStatus(SysConstants.WO_PENDING_AUDIT);
        wo.setProcessResult(dto.getProcessResult());
        wo.setProcessImages(dto.getProcessImages());
        updateById(wo);
    }

    @Override
    public void audit(String id, String opinion, boolean passed) {
        WorkOrder wo = getById(id);
        if (wo == null) throw new BizException("工单不存在");
        if (wo.getOrderStatus() != SysConstants.WO_PENDING_AUDIT) throw new BizException("当前状态不可审核");
        wo.setOrderStatus(passed ? SysConstants.WO_COMPLETED : SysConstants.WO_PROCESSING);
        wo.setAuditOpinion(opinion);
        wo.setAuditedAt(LocalDateTime.now());
        if (passed) wo.setCompletedAt(LocalDateTime.now());
        updateById(wo);
    }

    @Override
    public Map<String, Object> statistics() {
        Map<String, Object> stats = new HashMap<>();
        for (int s = 0; s <= 4; s++) {
            stats.put("status" + s, lambdaQuery().eq(WorkOrder::getOrderStatus, s).count());
        }
        stats.put("today", lambdaQuery()
                .ge(WorkOrder::getCreatedAt, LocalDateTime.now().toLocalDate().atStartOfDay())
                .count());
        return stats;
    }

    private String generateOrderNo() {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return "WO" + date + RandomUtil.randomNumbers(4);
    }

    private WorkOrderVO toVO(WorkOrder wo) {
        WorkOrderVO vo = new WorkOrderVO();
        BeanUtil.copyProperties(wo, vo);
        int status = wo.getOrderStatus() != null ? wo.getOrderStatus() : 0;
        if (status >= 0 && status < STATUS_NAMES.length) vo.setOrderStatusName(STATUS_NAMES[status]);
        int pri = wo.getPriority() != null ? wo.getPriority() : 3;
        if (pri > 0 && pri < PRIORITY_NAMES.length) vo.setPriorityName(PRIORITY_NAMES[pri]);
        return vo;
    }
}
