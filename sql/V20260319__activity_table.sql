-- 活动信息表（核心表）
CREATE TABLE IF NOT EXISTS sys_activity (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  activity_name VARCHAR(100) NOT NULL COMMENT '活动名称',
  activity_type VARCHAR(50) NOT NULL COMMENT '活动类型：课堂活动/户外活动/午睡活动',
  class_name VARCHAR(50) NOT NULL COMMENT '参与班级',
  activity_place VARCHAR(100) COMMENT '活动地点',
  preset_duration INT NOT NULL COMMENT '预设时长(分钟)',
  actual_duration INT DEFAULT 0 COMMENT '实际时长(分钟)',
  activity_status VARCHAR(20) DEFAULT '未开始' COMMENT '活动状态：未开始/监控中/已完成/超时完成',
  create_by VARCHAR(64) DEFAULT '' COMMENT '创建者',
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_by VARCHAR(64) DEFAULT '' COMMENT '更新者',
  update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动信息表';

-- 活动监控记录表（记录计时/提醒日志）
CREATE TABLE IF NOT EXISTS sys_activity_monitor (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  activity_id BIGINT NOT NULL COMMENT '活动ID',
  start_time DATETIME COMMENT '活动开始时间',
  end_time DATETIME COMMENT '活动结束时间',
  remind_time DATETIME COMMENT '超时提醒时间',
  remark VARCHAR(500) COMMENT '监控备注（如：超时10分钟提醒）',
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动监控记录表';

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