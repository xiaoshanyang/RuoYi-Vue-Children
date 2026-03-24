drop table if exists sys_food_info;
CREATE TABLE `sys_food_info` (
  `food_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '食材ID',
  `food_name` varchar(50) NOT NULL COMMENT '食材名称',
  `food_code` varchar(32) DEFAULT NULL COMMENT '食材编码',
  `food_type` varchar(20) NOT NULL COMMENT '食材类型（字典：food_type）',
  `spec` varchar(100) DEFAULT NULL COMMENT '规格（如：一级、无公害、有机）',
  `unit` varchar(20) NOT NULL COMMENT '计量单位（斤/公斤/箱/袋）',
  `warning_days` int(11) DEFAULT 0 COMMENT '保质期预警天数',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`food_id`),
  UNIQUE KEY `uk_food_name` (`food_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='食材基础信息表';



drop table if exists sys_supplier;
-- 供应商表
CREATE TABLE `sys_supplier` (
  `supplier_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '供应商ID',
  `supplier_name` varchar(100) NOT NULL COMMENT '供应商名称',
  `supplier_code` varchar(32) DEFAULT NULL COMMENT '供应商编码',
  `contact` varchar(20) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `business_license` varchar(255) DEFAULT NULL COMMENT '营业执照号',
  `food_license` varchar(255) DEFAULT NULL COMMENT '食品经营许可证',

  -- 核心审核状态
  -- 新增字段
  `audit_type` varchar(20) DEFAULT NULL COMMENT '审核类型：add-新增审核 disable-停用审核 enable-启用审核',
  `operate_desc` varchar(500) DEFAULT NULL COMMENT '操作描述（后勤填写：如“新增XX食材供应商”“申请停用违规供应商”）',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态 0 = 待审核、1 = 正常、2 = 停用、3 = 驳回、4 = 待停用、5 = 待启用',
  `reason` varchar(500) DEFAULT NULL COMMENT '审核意见/驳回原因/停用原因',

  `create_by` varchar(64) DEFAULT '' COMMENT '创建人（后勤）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `audit_by` varchar(64) DEFAULT NULL COMMENT '审核人（园长）',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',

  `update_by` varchar(64) DEFAULT '',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='供应商信息表';


-- 采购申请表
CREATE TABLE IF NOT EXISTS sys_purchase (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  food_name VARCHAR(50) NOT NULL COMMENT '食材名称',
  specification VARCHAR(50) COMMENT '食材规格',
  purchase_quantity INT NOT NULL COMMENT '采购数量',
  supplier_id BIGINT NOT NULL COMMENT '供应商ID',
  supplier_name VARCHAR(50) COMMENT '供应商名称',
  purchase_budget DECIMAL(10,2) COMMENT '采购预算(元)',
  purchase_status VARCHAR(20) DEFAULT '待审核' COMMENT '采购状态：待审核/审核失败/已审核/验收合格/验收不合格',
  remark VARCHAR(500) COMMENT '采购备注',
  create_by VARCHAR(64) DEFAULT '' COMMENT '创建者',
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_by VARCHAR(64) DEFAULT '' COMMENT '更新者',
  update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='食材采购申请表';


-- 采购验收表
CREATE TABLE IF NOT EXISTS sys_purchase_check (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  purchase_id BIGINT NOT NULL COMMENT '采购ID',
  check_status VARCHAR(10) COMMENT '验收状态：合格/不合格',
  check_result VARCHAR(500) COMMENT '验收结果',
  check_voucher VARCHAR(255) COMMENT '验收凭证路径',
  create_abnormal CHAR(1) DEFAULT '0' COMMENT '是否创建异常：0否 1是',
  check_by VARCHAR(64) DEFAULT '' COMMENT '验收人',
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '验收时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='食材采购验收表';



INSERT INTO `ry-vue`.`sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) 
VALUES ('食材安全管理', 0, 5, 'food-safety', NULL, NULL, '', 1, 0, 'M', '0', '0', 'system:foodSafety:view', '#', 'admin', '2026-03-24 15:37:26', '', NULL, '');


-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('食材管理', '3', '1', 'foodInfo', 'system/foodSafety/foodInfo/index', 1, 0, 'C', '0', '0', 'system:foodInfo:list', '#', 'admin', sysdate(), '', null, '食材管理菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('食材管理查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:foodInfo:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('食材管理新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:foodInfo:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('食材管理修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:foodInfo:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('食材管理删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:foodInfo:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('食材管理导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:foodInfo:export',       '#', 'admin', sysdate(), '', null, '');


-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('供应商信息', '3', '1', 'supplier', 'system/foodSafety/supplier/index', 1, 0, 'C', '0', '0', 'system:supplier:list', '#', 'admin', sysdate(), '', null, '供应商信息菜单');

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