package com.hwsmp.common.constant;

/**
 * 系统常量
 */
public interface SysConstants {
    /** 告警等级 */
    int ALARM_LEVEL_FIRE     = 1;  // 一级火警
    int ALARM_LEVEL_SUSPECT  = 2;  // 二级疑似
    int ALARM_LEVEL_ENV      = 3;  // 三级环境
    int ALARM_LEVEL_TIP      = 4;  // 四级提示

    /** 工单状态 */
    int WO_PENDING_ASSIGN = 0;  // 待派发
    int WO_PENDING_ACCEPT = 1;  // 待接单
    int WO_PROCESSING     = 2;  // 处理中
    int WO_PENDING_AUDIT  = 3;  // 待审核
    int WO_COMPLETED      = 4;  // 已完成
    int WO_ARCHIVED       = 5;  // 已归档

    /** 角色编码 */
    String ROLE_ADMIN       = "ADMIN";
    String ROLE_OPERATOR    = "OPERATOR";
    String ROLE_RANGER      = "RANGER";
    String ROLE_MAINTAINER  = "MAINTAINER";

    /** 设备类型 */
    int DEVICE_LASER_PTZ  = 1;  // 激光云台
    int DEVICE_CAMERA     = 2;  // 摄像头
    int DEVICE_SENSOR     = 3;  // 传感器
    int DEVICE_DRONE_DOCK = 4;  // 无人机机场
    int DEVICE_DOME       = 5;  // 球机
}
