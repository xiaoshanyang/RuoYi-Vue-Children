CREATE TABLE class_info (
    class_id BIGINT AUTO_INCREMENT COMMENT '班级ID',
    class_code VARCHAR(50) NOT NULL COMMENT '固定编号 C202401',
    class_name VARCHAR(50) NOT NULL COMMENT '当前名称 小一班',
    grade INT NOT NULL COMMENT '年级 1小班 2中班 3大班',
    entry_year INT NOT NULL COMMENT '入园年份 2025',
    teacher VARCHAR(30) COMMENT '班主任',
    status CHAR(1) DEFAULT '0' COMMENT '0正常 1毕业',
    create_time DATETIME,
    remark VARCHAR(255),
    PRIMARY KEY (class_id)
) ENGINE=InnoDB COMMENT='班级信息';

CREATE TABLE activity (
       activity_id BIGINT AUTO_INCREMENT COMMENT '活动ID',
      class_id BIGINT COMMENT '班级ID',
      class_code VARCHAR(50) NOT NULL COMMENT '快照编号',
      grade_name VARCHAR(20) NOT NULL COMMENT '快照年级 小班',
      class_full_name VARCHAR(50) NOT NULL COMMENT '快照班级 小一班',
      activity_name VARCHAR(100) NOT NULL COMMENT '活动名称',
      activity_time DATETIME COMMENT '活动时间',
      place VARCHAR(100) COMMENT '活动地点',
      charge_by VARCHAR(30) COMMENT '负责人',
      content VARCHAR(500) COMMENT '活动内容',
      create_time DATETIME,
      remark VARCHAR(255),
      PRIMARY KEY (activity_id)
) ENGINE=InnoDB COMMENT='活动信息';

CREATE TABLE activity_monitor (
      monitor_id BIGINT AUTO_INCREMENT COMMENT '监控ID',
      activity_id BIGINT NOT NULL COMMENT '活动ID',
      monitor_time DATETIME COMMENT '监控时间',
      monitor_status INT DEFAULT 0 COMMENT '0正常 1异常',
      order_status INT COMMENT '秩序 0好 1一般 2差',
      safe_hidden INT COMMENT '安全隐患 0无 1有',
      child_emotion INT COMMENT '情绪 0好 1一般 2差',
      abnormal_desc VARCHAR(500) COMMENT '异常描述',
      handle_method VARCHAR(500) COMMENT '处理措施',
      handle_result VARCHAR(500) COMMENT '处理结果',
      monitor_by VARCHAR(30) COMMENT '监控人',
      create_time DATETIME,
      PRIMARY KEY (monitor_id)
) ENGINE=InnoDB COMMENT='活动监控记录';
-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动监控记录', '3', '1', 'monitor', 'system/monitor/index', 1, 0, 'C', '0', '0', 'system:monitor:list', '#', 'admin', sysdate(), '', null, '活动监控记录菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动监控记录查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:monitor:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动监控记录新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:monitor:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动监控记录修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:monitor:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动监控记录删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:monitor:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动监控记录导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:monitor:export',       '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动信息', '3', '1', 'activity', 'system/activity/index', 1, 0, 'C', '0', '0', 'system:activity:list', '#', 'admin', sysdate(), '', null, '活动信息菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动信息查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:activity:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动信息新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:activity:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动信息修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:activity:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动信息删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:activity:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动信息导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:activity:export',       '#', 'admin', sysdate(), '', null, '');