-- H2 种子数据
INSERT INTO sys_role (id, role_name, role_code, description, status) VALUES
(1, '管理员', 'ADMIN', '系统管理员，拥有所有权限', 1),
(2, '值班操作员', 'OPERATOR', '指挥中心值班员', 1),
(3, '巡逻员', 'RANGER', '现场巡逻人员', 1),
(4, '维保人员', 'MAINTAINER', '设备维保人员', 1);

INSERT INTO sys_user (id, username, real_name, phone, role_code, status) VALUES
(1, 'admin', '系统管理员', '13800000001', 'ADMIN', 1),
(2, 'operator01', '值班员张三', '13800000002', 'OPERATOR', 1),
(3, 'ranger01', '巡逻员李四', '13800000003', 'RANGER', 1),
(4, 'maintainer01', '维保员王五', '13800000004', 'MAINTAINER', 1);

INSERT INTO sys_config (id, config_key, config_value, config_type, description) VALUES
(1, 'alarm.record.days', '90', 'int', '告警录像保存天数'),
(2, 'alarm.night.start', '20:00', 'time', '夜间模式开始时间'),
(3, 'alarm.night.end', '06:00', 'time', '夜间模式结束时间'),
(4, 'alarm.confirm.timeout', '300', 'int', '告警确认超时(秒)'),
(5, 'device.flow.warn_percent', '80', 'int', '4G流量预警百分比'),
(6, 'workorder.timeout.hours', '24', 'int', '工单超时时长(小时)');
