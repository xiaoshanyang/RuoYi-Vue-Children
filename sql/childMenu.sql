-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('幼儿信息（无冗余班级名称）', '3', '1', 'child', 'system/child/index', 1, 0, 'C', '0', '0', 'system:child:list', '#', 'admin', sysdate(), '', null, '幼儿信息（无冗余班级名称）菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('幼儿信息（无冗余班级名称）查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:child:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('幼儿信息（无冗余班级名称）新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:child:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('幼儿信息（无冗余班级名称）修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:child:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('幼儿信息（无冗余班级名称）删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:child:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('幼儿信息（无冗余班级名称）导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:child:export',       '#', 'admin', sysdate(), '', null, '');


-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('班级信息（按届命名）', '3', '1', 'class', 'system/class/index', 1, 0, 'C', '0', '0', 'system:class:list', '#', 'admin', sysdate(), '', null, '班级信息（按届命名）菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('班级信息（按届命名）查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:class:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('班级信息（按届命名）新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:class:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('班级信息（按届命名）修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:class:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('班级信息（按届命名）删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:class:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('班级信息（按届命名）导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:class:export',       '#', 'admin', sysdate(), '', null, '');