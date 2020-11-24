CREATE TABLE `auth_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `username` varchar(1024) NOT NULL COMMENT '用户名，英文，默认是姓名的小写拼音',
  `password` char(8) NOT NULL COMMENT '账号的密码',
  `name` varchar(1024) NOT NULL COMMENT '员工姓名，中文',
  `remark` varchar(1024) DEFAULT NULL COMMENT '账号的说明备注',
  `gmt_create` datetime NOT NULL COMMENT '账号的创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '账号的更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='账号表，电商公司里一个员工就对应着一个账号，每个账号给分配多个角色，同时这个账号也可以给分配多个权限';



CREATE TABLE `commodity_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint(20) NOT NULL COMMENT '类目ID',
  `brand_id` bigint(20) NOT NULL COMMENT '品牌ID',
  `code` varchar(1024) NOT NULL COMMENT 'SPU编号',
  `name` varchar(1024) NOT NULL COMMENT '商品名称',
  `sub_name` varchar(1024) NOT NULL COMMENT '商品副名称',
  `gross_weight` decimal(8,2) NOT NULL COMMENT '商品毛重，单位是g',
  `length` decimal(8,2) NOT NULL COMMENT '外包装长度，单位是cm',
  `width` decimal(8,2) NOT NULL COMMENT '外包装宽，单位是cm',
  `height` decimal(8,2) NOT NULL COMMENT '外包装高，单位是cm',
  `status` tinyint(4) NOT NULL COMMENT '商品状态，1：待审核，2：待上架，3：审核未通过，4：已上架',
  `service_guarantees` varchar(1024) NOT NULL COMMENT '服务保证',
  `package_list` varchar(1024) NOT NULL COMMENT '包装清单',
  `freight_template_id` bigint(20) NOT NULL COMMENT '运费模板ID',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_category_id` (`category_id`),
  KEY `idx_brand_id` (`brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='商品中心的商品信息基础表';

CREATE TABLE `commodity_goods_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` bigint(20) NOT NULL COMMENT '商品ID',
  `detail_content` longtext COMMENT '商品详情内容',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_goods_id` (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='商品详情数据';



CREATE TABLE `order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_account_id` bigint(20) DEFAULT NULL COMMENT '用户账号ID',
  `username` varchar(1024) NOT NULL COMMENT '用户名',
  `order_no` varchar(1024) NOT NULL COMMENT '订单编号，随机生成的UUID',
  `order_status` tinyint(4) NOT NULL COMMENT '订单状态，1：待付款，2：已取消，3：待发货，4：待收货，5：已完成，6：售后中（退货申请待审核），7：交易关闭（退货审核不通过），8：交易中（待寄送退货商品），9：售后中（退货商品待收货），10：售后中（退货待入库），11：（1）售后中（退货已入库），12：交易关闭（完成退款）',
  `consignee` varchar(1024) NOT NULL COMMENT '收货人',
  `delivery_address` varchar(1024) NOT NULL COMMENT '收货地址',
  `consignee_cell_phone_number` varchar(1024) NOT NULL COMMENT '收货人电话号码',
  `freight` decimal(8,2) NOT NULL COMMENT '运费',
  `pay_type` tinyint(4) NOT NULL COMMENT '支付方式，1：支付宝，2：微信',
  `total_amount` decimal(8,2) NOT NULL COMMENT '订单总金额',
  `discount_amount` decimal(8,2) NOT NULL COMMENT '促销活动折扣金额',
  `coupon_amount` decimal(8,2) NOT NULL COMMENT '优惠券抵扣金额',
  `payable_amount` decimal(8,2) NOT NULL COMMENT '应付金额，订单总金额 - 促销活动折扣金额 - 优惠券抵扣金额 + 运费',
  `invoice_title` varchar(1024) NOT NULL COMMENT '发票抬头',
  `taxpayer_id` varchar(1024) NOT NULL COMMENT '纳税人识别号',
  `order_comment` varchar(1024) NOT NULL COMMENT '订单备注',
  `is_published_comment` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否发表评论，1：发表，0：未发表',
  `confirm_receipt_time` datetime DEFAULT NULL COMMENT '确认收货时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_account_id` (`user_account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='订单信息表';