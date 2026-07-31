package com.hwsmp.workorder.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hwsmp.workorder.model.dto.*;
import com.hwsmp.workorder.model.entity.WorkOrder;
import com.hwsmp.workorder.model.vo.WorkOrderVO;

import java.util.Map;

public interface WorkOrderService extends IService<WorkOrder> {
    Page<WorkOrderVO> queryPage(WorkOrderQueryDTO query);
    WorkOrderVO getDetail(String id);
    String create(WorkOrderDTO dto);
    void dispatch(String id, Long assigneeId);
    void accept(String id);
    void process(WorkOrderProcessDTO dto);
    void audit(String id, String opinion, boolean passed);
    Map<String, Object> statistics();
}
