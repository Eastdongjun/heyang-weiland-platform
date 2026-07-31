-- =====================================================
-- 合阳洽川湿地智慧管理平台 - 数据库初始化脚本
-- PostgreSQL 16 + PostGIS
-- =====================================================

-- 启用 PostGIS 扩展
CREATE EXTENSION IF NOT EXISTS postgis;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- =====================================================
-- 1. 区域管理表
-- =====================================================
CREATE TABLE sys_region (
    id              BIGINT PRIMARY KEY,
    parent_id       BIGINT        DEFAULT 0,
    region_name     VARCHAR(64)   NOT NULL,
    region_code     VARCHAR(32),
    region_level    SMALLINT      NOT NULL DEFAULT 1,  -- 1=湿地 2=分区 3=网格
    sort_order      INT           DEFAULT 0,
    status          SMALLINT      DEFAULT 1,           -- 1=启用 0=禁用
    created_at      TIMESTAMPTZ   DEFAULT NOW(),
    updated_at      TIMESTAMPTZ   DEFAULT NOW()
);
COMMENT ON TABLE sys_region IS '区域管理表';

-- =====================================================
-- 2. 用户表
-- =====================================================
CREATE TABLE sys_user (
    id              BIGINT PRIMARY KEY,
    username        VARCHAR(32)   NOT NULL,
    real_name       VARCHAR(32)   NOT NULL,
    phone           VARCHAR(16)   NOT NULL UNIQUE,
    password        VARCHAR(128),
    avatar          VARCHAR(255),
    gender          SMALLINT      DEFAULT 0,           -- 0=未知 1=男 2=女
    email           VARCHAR(64),
    region_id       BIGINT        DEFAULT 0,
    role_code       VARCHAR(32)   NOT NULL DEFAULT 'RANGER', -- ADMIN/OPERATOR/RANGER/MAINTAINER
    status          SMALLINT      DEFAULT 1,           -- 1=正常 2=禁用
    last_login_at   TIMESTAMPTZ,
    last_login_ip   VARCHAR(64),
    is_deleted      SMALLINT      DEFAULT 0,
    created_at      TIMESTAMPTZ   DEFAULT NOW(),
    updated_at      TIMESTAMPTZ   DEFAULT NOW()
);
COMMENT ON TABLE sys_user IS '系统用户表';
CREATE INDEX idx_user_phone ON sys_user(phone);
CREATE INDEX idx_user_region ON sys_user(region_id);

-- =====================================================
-- 3. 角色权限表
-- =====================================================
CREATE TABLE sys_role (
    id              BIGINT PRIMARY KEY,
    role_code       VARCHAR(32)   NOT NULL UNIQUE,     -- ADMIN/OPERATOR/RANGER/MAINTAINER
    role_name       VARCHAR(32)   NOT NULL,
    description     VARCHAR(255),
    status          SMALLINT      DEFAULT 1,
    created_at      TIMESTAMPTZ   DEFAULT NOW(),
    updated_at      TIMESTAMPTZ   DEFAULT NOW()
);

CREATE TABLE sys_menu (
    id              BIGINT PRIMARY KEY,
    parent_id       BIGINT        DEFAULT 0,
    menu_name       VARCHAR(32)   NOT NULL,
    menu_code       VARCHAR(64)   NOT NULL,
    menu_type       SMALLINT      DEFAULT 1,           -- 1=菜单 2=按钮 3=接口
    path            VARCHAR(128),
    component       VARCHAR(128),
    icon            VARCHAR(64),
    sort_order      INT           DEFAULT 0,
    visible         SMALLINT      DEFAULT 1,
    created_at      TIMESTAMPTZ   DEFAULT NOW()
);

CREATE TABLE sys_role_menu (
    id              BIGINT PRIMARY KEY,
    role_id         BIGINT        NOT NULL,
    menu_id         BIGINT        NOT NULL,
    UNIQUE (role_id, menu_id)
);
COMMENT ON TABLE sys_role IS '角色表';
COMMENT ON TABLE sys_menu IS '菜单权限表';
COMMENT ON TABLE sys_role_menu IS '角色菜单关联表';

-- =====================================================
-- 4. 设备表
-- =====================================================
CREATE TABLE sys_device (
    id              BIGINT PRIMARY KEY,
    device_name     VARCHAR(64)   NOT NULL,
    device_code     VARCHAR(64)   NOT NULL UNIQUE,
    device_type     SMALLINT      NOT NULL,            -- 1=激光云台 2=摄像头 3=传感器 4=无人机机场 5=球机
    protocol        VARCHAR(16),                       -- RTSP/ONVIF/GB28181/MQTT/Modbus
    ip_address      VARCHAR(64),
    port            INT,
    username        VARCHAR(64),
    password        VARCHAR(128),
    region_id       BIGINT        DEFAULT 0,
    location_geom   GEOMETRY(POINT, 4326),
    location_text   VARCHAR(256),
    is_4g           SMALLINT      DEFAULT 0,           -- 0=有线 1=4G
    data_plan_gb    DECIMAL(10,2),                      -- 4G流量套餐(GB)
    data_used_gb    DECIMAL(10,2) DEFAULT 0,            -- 当月已用流量
    online_status   SMALLINT      DEFAULT 0,           -- 0=离线 1=在线
    last_heartbeat  TIMESTAMPTZ,
    maintain_cycle  INT           DEFAULT 30,           -- 维保周期(天)
    maintainer_id   BIGINT,
    last_maintain   TIMESTAMPTZ,
    status          SMALLINT      DEFAULT 1,
    is_deleted      SMALLINT      DEFAULT 0,
    created_at      TIMESTAMPTZ   DEFAULT NOW(),
    updated_at      TIMESTAMPTZ   DEFAULT NOW()
);
COMMENT ON TABLE sys_device IS '设备表';
CREATE INDEX idx_device_region ON sys_device(region_id);
CREATE INDEX idx_device_type ON sys_device(device_type);
CREATE INDEX idx_device_location ON sys_device USING GIST(location_geom);

-- =====================================================
-- 5. 告警表
-- =====================================================
CREATE TABLE sys_alarm (
    id              UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    alarm_source    VARCHAR(32)   NOT NULL,            -- ai_fire/ai_smoke/motion/iot/video_diag/fence/manual
    alarm_type      VARCHAR(32)   NOT NULL,            -- fire/smoke/motion/temp_high/wind_high/rain_heavy/device_offline
    alarm_level     SMALLINT      NOT NULL DEFAULT 3,  -- 1=一级(红色) 2=二级(橙色) 3=三级(黄色) 4=四级(灰色)
    alarm_status    VARCHAR(16)   NOT NULL DEFAULT 'NEW', -- NEW/ACK/PROCESSING/RESOLVED/CLOSED/FALSE_ALARM
    device_id       BIGINT,
    device_name     VARCHAR(128),
    location_geom   GEOMETRY(POINT, 4326),
    location_text   VARCHAR(256),
    confidence      REAL,                              -- AI置信度 0.0-1.0
    occurred_at     TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    acknowledged_at TIMESTAMPTZ,
    resolved_at     TIMESTAMPTZ,
    snapshots       JSONB,                             -- 截图URL数组
    video_clips     JSONB,                             -- 视频片段URL数组
    metadata        JSONB,                             -- 扩展信息JSON
    work_order_id   UUID,
    confirmed_by    BIGINT,
    false_reason    VARCHAR(32),                       -- 误报原因: device_mis/weather/animal/other
    false_remark    VARCHAR(256),
    is_deleted      SMALLINT      DEFAULT 0,
    created_at      TIMESTAMPTZ   DEFAULT NOW(),
    updated_at      TIMESTAMPTZ   DEFAULT NOW()
);
COMMENT ON TABLE sys_alarm IS '告警表';
CREATE INDEX idx_alarm_status ON sys_alarm(alarm_status);
CREATE INDEX idx_alarm_level ON sys_alarm(alarm_level);
CREATE INDEX idx_alarm_occurred ON sys_alarm(occurred_at DESC);
CREATE INDEX idx_alarm_location ON sys_alarm USING GIST(location_geom);
CREATE INDEX idx_alarm_device ON sys_alarm(device_id, occurred_at DESC);

-- =====================================================
-- 6. 工单表
-- =====================================================
CREATE TABLE sys_work_order (
    id              UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    order_no        VARCHAR(32)   NOT NULL UNIQUE,     -- 工单编号 WO202605080001
    title           VARCHAR(128)  NOT NULL,
    order_type      VARCHAR(32)   DEFAULT 'ALARM',     -- ALARM/INSPECT/EVENT/MAINTAIN
    priority        SMALLINT      DEFAULT 2,           -- 1=紧急 2=普通 3=低
    order_status    SMALLINT      DEFAULT 0,           -- 0=待派发 1=待接单 2=处理中 3=待审核 4=已完成 5=已归档
    alarm_id        UUID,
    device_id       BIGINT,
    location_geom   GEOMETRY(POINT, 4326),
    location_text   VARCHAR(256),
    description     TEXT,
    snapshots       JSONB,
    region_id       BIGINT,
    assignee_id     BIGINT,                            -- 指派人
    acceptor_id     BIGINT,                            -- 接单人
    accepted_at     TIMESTAMPTZ,
    arrived_at      TIMESTAMPTZ,
    handle_result   VARCHAR(32),                       -- 处置结果: done/false_alarm/need_support/unreachable
    handle_remark   VARCHAR(512),
    handle_photos   JSONB,
    handle_video    JSONB,
    completed_at    TIMESTAMPTZ,
    auditor_id      BIGINT,
    audit_opinion   VARCHAR(512),
    audit_status    SMALLINT      DEFAULT 0,           -- 0=未审核 1=通过 2=驳回
    audited_at      TIMESTAMPTZ,
    reopened        SMALLINT      DEFAULT 0,           -- 0=正常 1=改派
    reassign_reason VARCHAR(256),
    is_deleted      SMALLINT      DEFAULT 0,
    created_by      BIGINT,
    created_at      TIMESTAMPTZ   DEFAULT NOW(),
    updated_at      TIMESTAMPTZ   DEFAULT NOW()
);
COMMENT ON TABLE sys_work_order IS '工单表';
CREATE INDEX idx_wo_status ON sys_work_order(order_status);
CREATE INDEX idx_wo_assignee ON sys_work_order(assignee_id);
CREATE INDEX idx_wo_location ON sys_work_order USING GIST(location_geom);

-- =====================================================
-- 7. 操作审计日志表
-- =====================================================
CREATE TABLE sys_audit_log (
    id              BIGINT PRIMARY KEY,
    operator_id     BIGINT,
    operator_name   VARCHAR(32),
    operation_type  VARCHAR(32)   NOT NULL,            -- LOGIN/SETTINGS/ALARM/WORKORDER/DEVICE/USER
    target_type     VARCHAR(32),                       -- 操作对象类型
    target_id       VARCHAR(64),                       -- 操作对象ID
    operation_desc  VARCHAR(256),
    request_params  JSONB,
    response_result JSONB,
    ip_address      VARCHAR(64),
    user_agent      VARCHAR(512),
    duration_ms     INT,
    created_at      TIMESTAMPTZ   DEFAULT NOW()
);
COMMENT ON TABLE sys_audit_log IS '操作审计日志表';
CREATE INDEX idx_audit_operator ON sys_audit_log(operator_id, created_at DESC);
CREATE INDEX idx_audit_type ON sys_audit_log(operation_type, created_at DESC);

-- =====================================================
-- 8. 消息通知表
-- =====================================================
CREATE TABLE sys_notification (
    id              BIGINT PRIMARY KEY,
    user_id         BIGINT        NOT NULL,
    title           VARCHAR(128)  NOT NULL,
    content         TEXT,
    msg_type        VARCHAR(32)   DEFAULT 'SYSTEM',    -- SYSTEM/WORKORDER/ALARM/WEATHER
    business_type   VARCHAR(32),
    business_id     VARCHAR(64),
    is_read         SMALLINT      DEFAULT 0,
    read_at         TIMESTAMPTZ,
    created_at      TIMESTAMPTZ   DEFAULT NOW()
);
COMMENT ON TABLE sys_notification IS '消息通知表';
CREATE INDEX idx_notify_user ON sys_notification(user_id, is_read, created_at DESC);

-- =====================================================
-- 9. 系统配置表
-- =====================================================
CREATE TABLE sys_config (
    id              BIGINT PRIMARY KEY,
    config_key      VARCHAR(64)   NOT NULL UNIQUE,
    config_value    TEXT,
    config_desc     VARCHAR(256),
    created_at      TIMESTAMPTZ   DEFAULT NOW(),
    updated_at      TIMESTAMPTZ   DEFAULT NOW()
);
COMMENT ON TABLE sys_config IS '系统配置表';

-- =====================================================
-- 10. 初始化种子数据
-- =====================================================
-- 角色
INSERT INTO sys_role (id, role_code, role_name, description) VALUES
(1, 'ADMIN',       '系统管理员',   '全部权限，系统配置、用户管理'),
(2, 'OPERATOR',    '值班操作员',   '指挥中心值班操作，告警/工单处理'),
(3, 'RANGER',      '巡逻员',       '仅移动端App，接收任务、现场处置'),
(4, 'MAINTAINER',  '维保人员',     '设备管理与维保');

-- 管理员用户 (密码: admin123, BCrypt加密)
INSERT INTO sys_user (id, username, real_name, phone, password, role_code, status) VALUES
(1, 'admin', '系统管理员', '13800000000', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EHsM8lE', 'ADMIN', 1);

-- 系统配置默认值
INSERT INTO sys_config (id, config_key, config_value, config_desc) VALUES
(1, 'alarm.fire.temp_threshold', '35', '火情检测温度阈值(℃)'),
(2, 'alarm.fire.smoke_threshold', '70', '烟雾检测置信度阈值(%)'),
(3, 'alarm.env.wind_threshold', '10.8', '大风告警阈值(m/s, 6级)'),
(4, 'alarm.env.temp_threshold', '40', '高温告警阈值(℃)'),
(5, 'alarm.env.rain_threshold', '20', '暴雨告警阈值(mm/h)'),
(6, 'video.retention_days', '30', '录像保留天数'),
(7, 'night_mode.start', '19:00', '夜间模式开始时间'),
(8, 'night_mode.end', '07:00', '夜间模式结束时间');
