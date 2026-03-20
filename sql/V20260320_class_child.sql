-- 班级信息表（按届命名版）
CREATE TABLE IF NOT EXISTS sys_class (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID（唯一标识）',
  grade_year VARCHAR(4) NOT NULL COMMENT '届数（如：2025、2026）',
  class_code VARCHAR(20) NOT NULL COMMENT '班级编码（如：小一、中二、大一）',
  class_name VARCHAR(50) NOT NULL COMMENT '班级全称（如：2025级小一班）',
  class_type VARCHAR(10) NOT NULL COMMENT '班级类型：小班/中班/大班',
  teacher_id BIGINT COMMENT '班主任ID（关联sys_user.id）',
  teacher_name VARCHAR(50) COMMENT '班主任姓名',
  student_count INT DEFAULT 0 COMMENT '幼儿人数',
  status CHAR(1) DEFAULT '0' COMMENT '状态：0正常 1停用',
  create_by VARCHAR(64) DEFAULT '' COMMENT '创建者',
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_by VARCHAR(64) DEFAULT '' COMMENT '更新者',
  update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY uk_class_name (class_name), -- 班级全称唯一
  KEY idx_grade_year (grade_year) -- 按届数索引，方便查询
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='班级信息表（按届命名）';

-- 初始化按届命名的班级数据（示例）
INSERT INTO sys_class (grade_year, class_code, class_name, class_type, status) VALUES 
('2025', '小一', '2025级小一班', '小班', '0'),
('2025', '小二', '2025级小二班', '小班', '0'),
('2024', '中一', '2024级中一班', '中班', '0'),
('2023', '大一', '2023级大一班', '大班', '0');

-- 幼儿信息表（仅关联class_id版）
CREATE TABLE IF NOT EXISTS sys_child (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  child_name VARCHAR(50) NOT NULL COMMENT '幼儿姓名',
  class_id BIGINT NOT NULL COMMENT '班级ID（唯一关联sys_class.id）',
  gender CHAR(1) COMMENT '性别：0男 1女',
  birthday DATE COMMENT '出生日期',
  allergy_info VARCHAR(500) COMMENT '过敏信息（如：鸡蛋、牛奶）',
  parent_name VARCHAR(50) COMMENT '家长姓名',
  parent_phone VARCHAR(20) NOT NULL COMMENT '家长联系电话',
  enroll_time DATE COMMENT '入园时间',
  status CHAR(1) DEFAULT '0' COMMENT '状态：0正常 1请假 2退学',
  create_by VARCHAR(64) DEFAULT '' COMMENT '创建者',
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_by VARCHAR(64) DEFAULT '' COMMENT '更新者',
  update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id),
  KEY idx_class_id (class_id), -- 按班级ID索引，提升查询速度
  KEY idx_parent_phone (parent_phone) -- 按家长电话索引，方便联系
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='幼儿信息表（无冗余班级名称）';