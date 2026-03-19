-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('食材采购验收', '3', '1', 'check', 'system/check/index', 1, 0, 'C', '0', '0', 'system:check:list', '#', 'admin', sysdate(), '', null, '食材采购验收菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('食材采购验收查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:check:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('食材采购验收新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:check:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('食材采购验收修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:check:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('食材采购验收删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:check:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('食材采购验收导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:check:export',       '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('供应商信息', '3', '1', 'supplier', 'system/supplier/index', 1, 0, 'C', '0', '0', 'system:supplier:list', '#', 'admin', sysdate(), '', null, '供应商信息菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('供应商信息查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:supplier:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('供应商信息新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:supplier:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('供应商信息修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:supplier:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('供应商信息删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:supplier:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('供应商信息导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:supplier:export',       '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('食材采购申请', '3', '1', 'purchase', 'system/purchase/index', 1, 0, 'C', '0', '0', 'system:purchase:list', '#', 'admin', sysdate(), '', null, '食材采购申请菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('食材采购申请查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:purchase:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('食材采购申请新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:purchase:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('食材采购申请修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:purchase:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('食材采购申请删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:purchase:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('食材采购申请导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:purchase:export',       '#', 'admin', sysdate(), '', null, '');