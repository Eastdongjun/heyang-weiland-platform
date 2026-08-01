-- H2 初始化 DDL (兼容 PostgreSQL 语法)
CREATE TABLE IF NOT EXISTS sys_region (
    id BIGINT PRIMARY KEY,
    parent_id BIGINT DEFAULT 0,
    region_name VARCHAR(100) NOT NULL,
    region_level INT NOT NULL,
    region_code VARCHAR(50),
    sort_order INT DEFAULT 0,
    status INT DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS sys_user (
    id BIGINT PRIMARY KEY,
    username VARCHAR(50),
    real_name VARCHAR(50),
    phone VARCHAR(20) NOT NULL,
    password VARCHAR(255),
    avatar VARCHAR(500),
    gender INT,
    email VARCHAR(100),
    region_id BIGINT,
    role_code VARCHAR(30) DEFAULT 'RANGER',
    status INT DEFAULT 1,
    last_login_at TIMESTAMP,
    last_login_ip VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS sys_role (
    id BIGINT PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL,
    role_code VARCHAR(30) NOT NULL UNIQUE,
    description VARCHAR(200),
    status INT DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS sys_menu (
    id BIGINT PRIMARY KEY,
    parent_id BIGINT DEFAULT 0,
    menu_name VARCHAR(50) NOT NULL,
    menu_type INT,
    path VARCHAR(200),
    component VARCHAR(200),
    icon VARCHAR(100),
    sort_order INT DEFAULT 0,
    status INT DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS sys_role_menu (
    id BIGINT PRIMARY KEY,
    role_id BIGINT NOT NULL,
    menu_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS sys_device (
    id BIGINT PRIMARY KEY,
    device_name VARCHAR(100) NOT NULL,
    device_code VARCHAR(50) NOT NULL UNIQUE,
    device_type INT NOT NULL,
    device_model VARCHAR(100),
    gb_device_id VARCHAR(50),
    rtsp_main_url VARCHAR(500),
    rtsp_sub_url VARCHAR(500),
    longitude DECIMAL(12,8),
    latitude DECIMAL(12,8),
    location_text VARCHAR(200),
    region_id BIGINT,
    pic_url VARCHAR(500),
    device_ip VARCHAR(50),
    online_status INT DEFAULT 0,
    sim_card_no VARCHAR(20),
    monthly_flow_limit INT,
    monthly_flow_used INT DEFAULT 0,
    next_maintenance_date DATE,
    maintenance_cycle INT,
    install_date DATE,
    remark VARCHAR(500),
    status INT DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS sys_alarm (
    id VARCHAR(36) PRIMARY KEY,
    alarm_source VARCHAR(50),
    alarm_type VARCHAR(50),
    alarm_level INT,
    alarm_status VARCHAR(20) DEFAULT 'pending',
    device_id BIGINT,
    device_name VARCHAR(100),
    longitude DECIMAL(12,8),
    latitude DECIMAL(12,8),
    location_text VARCHAR(200),
    confidence DOUBLE,
    snapshots TEXT,
    video_clip_url VARCHAR(500),
    weather_info VARCHAR(200),
    description VARCHAR(500),
    occurred_at TIMESTAMP,
    confirmed_at TIMESTAMP,
    confirmed_by BIGINT,
    confirm_remark VARCHAR(500),
    work_order_id VARCHAR(36),
    ext_info TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS sys_work_order (
    id VARCHAR(36) PRIMARY KEY,
    order_no VARCHAR(30) NOT NULL UNIQUE,
    title VARCHAR(200) NOT NULL,
    priority INT DEFAULT 3,
    order_status INT DEFAULT 0,
    alarm_id VARCHAR(36),
    content TEXT,
    images TEXT,
    longitude DECIMAL(12,8),
    latitude DECIMAL(12,8),
    location_text VARCHAR(200),
    assignee_id BIGINT,
    assignee_name VARCHAR(50),
    assign_reason VARCHAR(500),
    accepted_at TIMESTAMP,
    process_result TEXT,
    process_images TEXT,
    audit_opinion VARCHAR(500),
    auditor_id BIGINT,
    audited_at TIMESTAMP,
    completed_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS sys_config (
    id BIGINT PRIMARY KEY,
    config_key VARCHAR(100) NOT NULL UNIQUE,
    config_value TEXT,
    config_type VARCHAR(50),
    description VARCHAR(200),
    status INT DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted INT DEFAULT 0
);
