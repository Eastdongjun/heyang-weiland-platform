package com.hwsmp.workorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwsmp.workorder.model.entity.WorkOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkOrderMapper extends BaseMapper<WorkOrder> {
}
