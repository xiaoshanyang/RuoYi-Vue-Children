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

-- 供应商表
CREATE TABLE IF NOT EXISTS sys_supplier (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  supplier_name VARCHAR(50) NOT NULL COMMENT '供应商名称',
  contact VARCHAR(20) COMMENT '联系人',
  phone VARCHAR(20) COMMENT '联系电话',
  status CHAR(1) DEFAULT '0' COMMENT '状态：0正常 1禁用',
  create_by VARCHAR(64) DEFAULT '' COMMENT '创建者',
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_by VARCHAR(64) DEFAULT '' COMMENT '更新者',
  update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='供应商信息表';

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