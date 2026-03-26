-- 1. 库存批次表（核心FIFO）
CREATE TABLE warehouse_batch (
  batch_id bigint PRIMARY KEY AUTO_INCREMENT,
  food_id bigint,
  food_name varchar(100),
  purchase_id bigint,
  check_id bigint,
  in_qty decimal(10,2),
  remain_qty decimal(10,2),
  in_time datetime,
  create_by varchar(64)
);

-- 2. 领用单主表
CREATE TABLE warehouse_out (
  out_id bigint PRIMARY KEY AUTO_INCREMENT,
  out_time datetime,
  create_by varchar(64),
  abnormal_status tinyint DEFAULT 0, -- 0正常 1异常
  abnormal_note varchar(500),
  remark varchar(500)
);

-- 3. 领用明细表（支持多产品+多批次）
CREATE TABLE warehouse_out_item (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  out_id bigint,
  food_id bigint,
  food_name varchar(100),
  out_qty decimal(10,2),
  batch_id bigint
);