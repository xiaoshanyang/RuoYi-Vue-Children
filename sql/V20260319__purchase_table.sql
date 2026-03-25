drop table if exists sys_food_info;
CREATE TABLE `sys_food_info` (
  `food_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '食材ID',
  `food_name` varchar(50) NOT NULL COMMENT '食材名称',
  `food_code` varchar(32) DEFAULT NULL COMMENT '食材编码',
  `food_type` varchar(20) NOT NULL COMMENT '食材类型（字典：food_type）',
  `spec` varchar(100) DEFAULT NULL COMMENT '规格（如：一级、无公害、有机）',
  `unit` varchar(20) NOT NULL COMMENT '计量单位（斤/公斤/箱/袋）',
  `price` decimal(10,2) DEFAULT 0 COMMENT '价格',
  `expire_days` int(11) DEFAULT 0 COMMENT '保质期(天)',
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

-- 采购单主表
CREATE TABLE sys_purchase (
    purchase_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '采购单ID',
    purchase_no VARCHAR(32) NOT NULL COMMENT '采购单号',
    supplier_id BIGINT NOT NULL COMMENT '供应商ID',
    supplier_name VARCHAR(100) COMMENT '供应商名称',
    total_amount DECIMAL(10,2) COMMENT '总金额',
    status CHAR(1) DEFAULT '0' COMMENT '状态 0待提交 1待审核 2通过 3驳回',
    remark VARCHAR(500) COMMENT '备注',
    audit_by VARCHAR(64) COMMENT '审核人',
    audit_time DATETIME COMMENT '审核时间',
    create_by VARCHAR(64) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(64) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='采购单主表';

-- 采购单明细表
CREATE TABLE sys_purchase_item (
    item_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '明细ID',
    purchase_id BIGINT NOT NULL COMMENT '采购单ID',
    food_id BIGINT NOT NULL COMMENT '食材ID',
    food_name VARCHAR(100) COMMENT '食材名称',
    category VARCHAR(32) COMMENT '食材类别',
    unit VARCHAR(32) COMMENT '计量单位',
    price DECIMAL(10,2) COMMENT '单价',
    qty DECIMAL(10,2) COMMENT '数量',
    sub_total DECIMAL(10,2) COMMENT '小计金额'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='采购单明细表';


-- 采购验收主表
CREATE TABLE sys_purchase_check (
    check_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '验收ID',
    purchase_id BIGINT NOT NULL COMMENT '采购单ID',
    purchase_no VARCHAR(32) COMMENT '采购单号',
    supplier_id BIGINT COMMENT '供应商ID',
    supplier_name VARCHAR(100) COMMENT '供应商名称',
    check_result CHAR(1) DEFAULT '0' COMMENT '验收结果 0=待验收 1=合格 2=不合格',
    check_remark VARCHAR(500) COMMENT '验收意见',
    check_by VARCHAR(64) COMMENT '验收人',
    check_time DATETIME COMMENT '验收时间',
    create_by VARCHAR(64) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(64) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='采购验收主表';

-- 采购验收明细表
CREATE TABLE sys_purchase_check_item (
    item_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '明细ID',
    check_id BIGINT NOT NULL COMMENT '验收ID',
    food_id BIGINT NOT NULL COMMENT '食材ID',
    food_name VARCHAR(100) COMMENT '食材名称',
    plan_qty DECIMAL(10,2) COMMENT '采购数量',
    real_qty DECIMAL(10,2) COMMENT '实收数量',
    expire_check CHAR(1) DEFAULT '1' COMMENT '保质期 0不合格 1合格',
    pesticide_check CHAR(1) DEFAULT '1' COMMENT '农残 0不合格 1合格',
    appearance_check CHAR(1) DEFAULT '1' COMMENT '外观 0不合格 1合格',
    item_result CHAR(1) DEFAULT '1' COMMENT '明细结果 0不合格 1合格'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='采购验收明细表';


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

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('供应商信息审核', @parentId, '6',  '#', '', 1, 0, 'F', '0', '0', 'system:supplier:audit',       '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('采购单管理', '3', '1', 'purchase', 'system/foodSafety/purchase/index', 1, 0, 'C', '0', '0', 'system:purchase:list', '#', 'admin', sysdate(), '', null, '采购单管理菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('采购单管理查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:purchase:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('采购单管理新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:purchase:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('采购单管理修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:purchase:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('采购单管理删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:purchase:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('采购单管理导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:purchase:export',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('采购单管理审核', @parentId, '6',  '#', '', 1, 0, 'F', '0', '0', 'system:purchase:audit',       '#', 'admin', sysdate(), '', null, '');

-- 采购验收管理菜单
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('采购验收管理', '3', '1', 'purchaseCheck', 'system/foodSafety/purchaseCheck/index', 1, 0, 'C', '0', '0', 'system:purchaseCheck:list', '#', 'admin', sysdate(), '', null, '采购验收管理菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('采购验收查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:purchaseCheck:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('采购验收新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:purchaseCheck:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('采购验收修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:purchaseCheck:edit',         '#', 'admin', sysdate(), '', null, ''); 