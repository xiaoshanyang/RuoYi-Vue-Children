CREATE TABLE class_teacher (
                               id BIGINT PRIMARY KEY AUTO_INCREMENT,
                               class_id BIGINT NOT NULL COMMENT '班级ID',
                               user_id BIGINT NOT NULL COMMENT '教师用户ID',
                               user_name VARCHAR(30) COMMENT '教师姓名',
                               is_leader CHAR(1) DEFAULT '0' COMMENT '是否班主任 1是 0否',
                               status CHAR(1) DEFAULT '0' COMMENT '状态 0在职 1调离/离职',
                               create_time DATETIME DEFAULT NOW(),
                               remark VARCHAR(255),
                               UNIQUE KEY uk_class_user (class_id, user_id)
) ENGINE=InnoDB COMMENT '班级教师关联表';

CREATE TABLE activity_template (
                                   template_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                   temp_name VARCHAR(100) NOT NULL COMMENT '活动名称',
                                   temp_content VARCHAR(500) COMMENT '活动内容说明',
                                   safe_notice VARCHAR(255) COMMENT '安全注意事项',
                                   create_by VARCHAR(30),
                                   create_time DATETIME,
                                   remark VARCHAR(255)
) ENGINE=InnoDB COMMENT='活动模板库';