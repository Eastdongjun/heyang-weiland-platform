-- 告警种子数据
INSERT INTO sys_alarm (id, alarm_source, alarm_type, alarm_level, alarm_status, device_id, device_name, longitude, latitude, location_text, confidence, description, occurred_at)
VALUES
('ALM-20260801-001', 'AI_VISUAL', 'FIRE', 1, 'pending', 2083279334621081601, '湿地东入口球机', 110.1235, 35.2346, '洽川湿地东入口东200米', 0.95, 'AI视频检测到疑似火点，置信度95%', '2026-08-01 09:30:00'),
('ALM-20260801-002', 'AI_THERMAL', 'SMOKE', 2, 'confirmed', 2083279334621081603, '观鸟台热成像', 110.1301, 35.2381, '观鸟台西北300米', 0.88, '热成像检测到温度异常升高，疑似烟雾', '2026-08-01 10:15:00'),
('ALM-20260801-003', 'VIDEO_QUALITY', 'FREEZE', 3, 'pending', 2083279334621081602, '湿地北岸枪机', 110.1290, 35.2401, '洽川湿地北岸', 0.0, '视频画面冻结超过30秒', '2026-08-01 11:00:00');
