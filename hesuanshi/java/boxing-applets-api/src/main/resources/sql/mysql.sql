/*
Navicat MySQL Data Transfer

Source Server         : 正式
Source Server Version : 50720
Source Host           : 121.196.198.16:3306
Source Database       : yzlh

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2017-12-15 10:22:06
*/

SET FOREIGN_KEY_CHECKS=0;


DROP TABLE IF EXISTS `box_type_classify`;
CREATE TABLE `box_type_classify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `status` int(4) DEFAULT 1 COMMENT '状态(0:不显示;1:显示;)',
  `sort` int(4) DEFAULT 1 COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='盒型分类';


DROP TABLE IF EXISTS `box_type_detail`;
CREATE TABLE `box_type_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `box_type_classify_id` bigint(20) NOT NULL COMMENT '盒型分类id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `image` varchar(255) NOT NULL COMMENT '图片',
  `valuation_formula` varchar(255) NOT NULL COMMENT '计价公式',
  `min_piece` int(4) DEFAULT 0 COMMENT '最小订购数量',
  `max_piece` int(4) DEFAULT 0 COMMENT '最大订购数量　',
  `status` int(4) DEFAULT 1 COMMENT '状态(0:不显示;1:显示;)',
  `sort` int(4) DEFAULT 1 COMMENT '排序',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='包装盒型';


DROP TABLE IF EXISTS `box_material_classify`;
CREATE TABLE `box_material_classify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `status` int(4) DEFAULT 1 COMMENT '状态(0:不显示;1:显示;)',
  `sort` int(4) DEFAULT 1 COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='材质分类';



DROP TABLE IF EXISTS `box_material_detail`;
CREATE TABLE `box_material_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `box_material_classify_id` bigint(20) NOT NULL COMMENT '材质分类id',
  `box_type_detail_id` bigint(20) NOT NULL COMMENT '包装盒型id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `image` varchar(255) NOT NULL COMMENT '图片',
  `price` decimal(18,2) DEFAULT '0.00' COMMENT '价格',
  `grams` decimal(18,2) DEFAULT '0.00' COMMENT '克数',
  `status` int(4) DEFAULT 1 COMMENT '状态(0:不显示;1:显示;)',
  `sort` int(4) DEFAULT 1 COMMENT '排序',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='包装材质';


DROP TABLE IF EXISTS `box_layout_classify`;
CREATE TABLE `box_layout_classify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `status` int(4) DEFAULT 1 COMMENT '状态(0:不显示;1:显示;)',
  `sort` int(4) DEFAULT 1 COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='版面分类';



DROP TABLE IF EXISTS `box_layout_detail`;
CREATE TABLE `box_layout_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `box_layout_classify_id` bigint(20) NOT NULL COMMENT '材质分类id',
  `box_type_detail_id` bigint(20) NOT NULL COMMENT '包装盒型id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `image` varchar(255) NOT NULL COMMENT '图片',
  `status` int(4) DEFAULT 1 COMMENT '状态(0:不显示;1:显示;)',
  `sort` int(4) DEFAULT 1 COMMENT '排序',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='包装版面';


DROP TABLE IF EXISTS `print_method`;
CREATE TABLE `print_method` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `status` int(4) DEFAULT 1 COMMENT '状态(0:不显示;1:显示;)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='印刷方式';


DROP TABLE IF EXISTS `print_method_price`;
CREATE TABLE `print_method_price` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `print_method_id` bigint(20) NOT NULL COMMENT '印刷方式id',
  `piece` int(4) DEFAULT 0 COMMENT '件数',
  `price` decimal(18,2) DEFAULT '0.00' COMMENT '价格',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='印刷方式价格区间';


DROP TABLE IF EXISTS `phone_code`;
CREATE TABLE `phone_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `phone` varchar(30) NOT NULL COMMENT '手机号',
  `code` varchar(30) NOT NULL COMMENT '验证码',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `end_time` datetime NOT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='手机验证码表';


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `avatar` varchar(255) NOT NULL COMMENT '头像',
  `nick_name` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '昵称',
  `gender` int(4) DEFAULT '0' COMMENT '性别(0:未知;1:男;2:女;)',
  `phone` varchar(30) DEFAULT NULL COMMENT '手机号',
  `province` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '省份',
  `city` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '市区',
  `unionid` varchar(50) NOT NULL COMMENT '微信开放平台唯一值',
  `quote_openid` varchar(50) DEFAULT NULL COMMENT '报价小程序openid',
  `service_number_openid` varchar(50) DEFAULT NULL COMMENT '服务号openid',
  `recommender_id` bigint(20) DEFAULT 0 COMMENT '推荐人id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户表';


DROP TABLE IF EXISTS `shipping_address`;
CREATE TABLE `shipping_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `name` varchar(30) NOT NULL COMMENT '姓名',
  `phone` varchar(30) NOT NULL COMMENT '手机号',
  `province` varchar(50) NOT NULL COMMENT '省份',
  `city` varchar(50) NOT NULL COMMENT '市区',
  `county` varchar(50) NOT NULL COMMENT '县区',
  `address` varchar(100) NOT NULL COMMENT '详情地址',
  `status` int(4) DEFAULT 0 COMMENT '类型(0:不是默认地址;1:默认地址;)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='收货地址表';


DROP TABLE IF EXISTS `express`;
CREATE TABLE `express` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `icon` varchar(255) NOT NULL COMMENT '图标',
  `name` varchar(30) NOT NULL COMMENT '名称',
  `type` varchar(30) NOT NULL COMMENT '别名',
  `phone` varchar(30) NOT NULL COMMENT '电话',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='快递表';


DROP TABLE IF EXISTS `order_user`;
CREATE TABLE `order_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `order_number` varchar(30) NOT NULL COMMENT '订单编号',
  `business_number` varchar(30) NOT NULL COMMENT '交易编号',
  `contact_name` varchar(30) NOT NULL COMMENT '买家姓名',
  `contact_phone` varchar(30) NOT NULL COMMENT '买家手机号',
  `contact_address` varchar(255) NOT NULL COMMENT '买家地址',
  `remark` varchar(255) DEFAULT NULL COMMENT '买家留言',
  `status` int(4) DEFAULT 0 COMMENT '订单状态(0：打样待确认；1:待生产; 2:待收货；3:已完成；11：取消订单(关闭)；)',
  `pay_status` int(4) DEFAULT 0 COMMENT '付款状态(0：待付定金；1：已付定金；2：已付尾款；3：已付全款；4：已退定金；5：已退款)',
  `merchant_status` int(4) DEFAULT '0' COMMENT '厂家状态(0: 未派单; 1: 已派单;2: 已接单;3: 已驳回)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `ship_time` datetime DEFAULT NULL COMMENT '发货时间',
  `pay_time` datetime DEFAULT NULL COMMENT '付款时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='订单表';


DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `type` int(4) DEFAULT 1 COMMENT '类型(1:已报价;2:未报价;)',
  `form_id` varchar(100) DEFAULT NULL COMMENT '通知用字段',
  `custom_number` varchar(30) NOT NULL COMMENT '编号',
  `name` varchar(30) NOT NULL COMMENT '名称',
  `type_id` bigint(20) NOT NULL COMMENT '盒型id',
  `type_name` varchar(30) NOT NULL COMMENT '盒型名称',
  `type_image` varchar(500) NOT NULL COMMENT '盒型图片',
  `material_id` bigint(20) NOT NULL COMMENT '材质id',
  `material_name` varchar(30) NOT NULL COMMENT '材质名称',
  `material_image` varchar(500) NOT NULL COMMENT '材质图片',
  `layout_id` bigint(20) NOT NULL COMMENT '版面id',
  `layout_name` varchar(30) NOT NULL COMMENT '版面名称',
  `layout_image` varchar(500) NOT NULL COMMENT '版面图片',
  `print_method_id` bigint(20) NOT NULL COMMENT '印刷方式id',
  `print_method_name` varchar(30) NOT NULL COMMENT '印刷方式名称',
  `length` decimal(18,2) DEFAULT '0.00'  COMMENT '长',
  `width` decimal(18,2) DEFAULT '0.00'  COMMENT '宽',
  `height` decimal(18,2) DEFAULT '0.00'  COMMENT '高',
  `piece` int(4) DEFAULT 0 COMMENT '件数',
  `price` decimal(18,2) DEFAULT '0.00' COMMENT '价格',
  `fare` decimal(18,2) DEFAULT '0.00' COMMENT '运费',
  `advance_payment_price`  decimal(18,2) DEFAULT '0.00' COMMENT '预付款金额',
  `status` int(4) DEFAULT '0' COMMENT '状态(0:待完成;1:已完成;2:退款中;3:已退款)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='订单详情表';


DROP TABLE IF EXISTS `order_express`;
CREATE TABLE `order_express` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `express_id` bigint(20) NOT NULL COMMENT '快递id',
  `serial` varchar(30) NOT NULL COMMENT '快递编号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='订单快递表';


DROP TABLE IF EXISTS `refund`;
CREATE TABLE `refund` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `order_detail_id` bigint(20) NOT NULL COMMENT '订单详情id',
  `refundNumber` varchar(30) NOT NULL COMMENT '退款编号',
  `reason` varchar(50) NOT NULL COMMENT '退款原因',
  `instruction` varchar(500) DEFAULT NULL COMMENT '退款说明',
  `money` decimal(18,2) DEFAULT '0.00' COMMENT '退款金额',
  `status` int(4) DEFAULT 0 COMMENT '状态(0:未退款;1:退款成功;2:退款失败;3:退款中)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`,`order_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='退款记录表';


DROP TABLE IF EXISTS `refund_file`;
CREATE TABLE `refund_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `refund_id` bigint(20) NOT NULL COMMENT '退款记录id',
  `resource` varchar(500) NOT NULL COMMENT '资源',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='退款记录附件表';

DROP TABLE IF EXISTS `custom_specification`;
CREATE TABLE `custom_specification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `type` int(4) DEFAULT 1 COMMENT '类型(1:已报价;2:未报价;)',
  `form_id` varchar(100) DEFAULT NULL COMMENT '通知用字段',
  `custom_number` varchar(30) NOT NULL COMMENT '编号',
  `name` varchar(30) NOT NULL COMMENT '名称',
  `type_id` bigint(20) NOT NULL COMMENT '盒型id',
  `type_name` varchar(30) NOT NULL COMMENT '盒型名称',
  `type_image` varchar(500) NOT NULL COMMENT '盒型图片',
  `material_id` bigint(20) NOT NULL COMMENT '材质id',
  `material_name` varchar(30) NOT NULL COMMENT '材质名称',
  `material_image` varchar(500) NOT NULL COMMENT '材质图片',
  `layout_id` bigint(20) NOT NULL COMMENT '版面id',
  `layout_name` varchar(30) NOT NULL COMMENT '版面名称',
  `layout_image` varchar(500) NOT NULL COMMENT '版面图片',
  `print_method_id` bigint(20) NOT NULL COMMENT '印刷方式id',
  `print_method_name` varchar(30) NOT NULL COMMENT '印刷方式名称',
  `length` decimal(18,2) DEFAULT '0.00'  COMMENT '长',
  `width` decimal(18,2) DEFAULT '0.00'  COMMENT '宽',
  `height` decimal(18,2) DEFAULT '0.00'  COMMENT '高',
  `piece` int(4) DEFAULT 0 COMMENT '件数',
  `price` decimal(18,2) DEFAULT '0.00' COMMENT '价格',
  `fare` decimal(18,2) DEFAULT '0.00' COMMENT '运费',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='定制规格表';


DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `type` int(4) DEFAULT 1 COMMENT '类型(1:定制;2:现货;)',
  `specification_id` bigint(20) NOT NULL COMMENT '规格id',
  `quantity` int(4) DEFAULT 0 COMMENT '数量',
  `selected` int(4) DEFAULT 1 COMMENT '是否选中(0:未选中;1:选中;)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='购物车表';

DROP TABLE IF EXISTS `user_company`;
CREATE TABLE `user_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `company_name` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '企业名称',
  `credit_code` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '统一社会信用代码',
  `province` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '省份',
  `city` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '市区',
  `address` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '详细地址',
  `business_license` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '营业执照图片地址',
  `status` int(4) DEFAULT 0 COMMENT '审核状态 :  0:待审核; 1: 通过; 2: 失败',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',  
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户公司信息表';

DROP TABLE IF EXISTS `delivery_config`;
CREATE TABLE `delivery_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `formula` varchar(100) NOT NULL COMMENT '公式',
  `first_weight` decimal(18,2) DEFAULT '0.00'  COMMENT '首重重量',
  `continued_weight` decimal(18,2) DEFAULT '0.00'  COMMENT '续重重量',
  `first_cost` decimal(18,2) DEFAULT '0.00'  COMMENT '首重费用',
  `continued_cost` decimal(18,2) DEFAULT '0.00'  COMMENT '续重费用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='配送配置表';

DROP TABLE IF EXISTS `promoter`;
CREATE TABLE `promoter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '名称',
  `real_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT '真实姓名',
  `phone` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT '手机号码',
  `content` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '资源优势',
  `status` int(4) DEFAULT '0' COMMENT '资格审查(0:未审核;1:审核通过;2: 不通过)',
  `commission_rate` double(16,2) DEFAULT NULL COMMENT '佣金比例',
  `number` int(4) DEFAULT 0 COMMENT '推广次数(分享和生成二维码时次数加一)',
  `form_id` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '通知用字段',
  `remark` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '审核理由',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='推广人员表';

DROP TABLE IF EXISTS `withdraw_record`;
CREATE TABLE `withdraw_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `money` decimal(18,2) DEFAULT '0.00' COMMENT '提现金额',
  `status` int(4) DEFAULT '0' COMMENT '提现状态(0:待处理;1:处理中,2:已处理)',
  `form_id` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '通知用字段',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='佣金提现记录表';

DROP TABLE IF EXISTS `commission_record`;
CREATE TABLE `commission_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户id',
  `order_detail_id` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT '订单详情id',
  `withdraw_record_id` bigint(20) DEFAULT NULL COMMENT '提现记录id',
  `price` decimal(18,2) DEFAULT '0.00' COMMENT '实际支付金额(可包含运费)',
  `commission` decimal(18,2) DEFAULT '0.00' COMMENT '佣金',
  `status` varchar(500) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '状态(0:未成功;1:可提现;2:提现中;3:已提现;)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='佣金记录表';


DROP TABLE IF EXISTS `poster`;
CREATE TABLE `poster` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `image` varchar(255) NOT NULL COMMENT '图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='海报';


DROP TABLE IF EXISTS `resource_file`;
CREATE TABLE `resource_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` int(4) DEFAULT '0' COMMENT '类型(1:方案,2:订单;)',
  `resource_number` varchar(30) NOT NULL COMMENT '资源编号',
  `file` varchar(500) NOT NULL COMMENT '文件',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='资源文件';

DROP TABLE IF EXISTS `wallet`;
CREATE TABLE `wallet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `money` decimal(18,2) DEFAULT '0.00' COMMENT '余额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='钱包表';


DROP TABLE IF EXISTS `wallet_record`;
CREATE TABLE `wallet_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `business_number` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT '交易编号',
  `type` int(4) DEFAULT '0' COMMENT '类型(0:充值;1:提现;)',
  `money` decimal(18,2) DEFAULT '0.00' COMMENT '金额',
  `status` int(4) DEFAULT '0' COMMENT '状态(0:未成功;1:成功;)',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='钱包记录表';

DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `phone` varchar(30) NOT NULL COMMENT '手机号',
  `contact` varchar(30) NOT NULL COMMENT '联系人',
  `logo` varchar(255) NOT NULL COMMENT '商户logo',
  `name` varchar(50) NOT NULL COMMENT '商户名称',
  `items` varchar(100) NOT NULL COMMENT '主营项目',
  `license` varchar(255) NOT NULL COMMENT '营业执照',
  `province` varchar(50) NOT NULL COMMENT '省份',
  `city` varchar(50) NOT NULL COMMENT '市区',
  `county` varchar(50) NOT NULL COMMENT '县区',
  `address` varchar(100) NOT NULL COMMENT '详情地址',
  `status` int(4) DEFAULT '0' COMMENT '资格审查(0:未审核;1:审核通过;2:不通过;)',
  `remark` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '审核理由',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='商户表';

DROP TABLE IF EXISTS `merchant_staff`;
CREATE TABLE `merchant_staff` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchant_id` bigint(20) NOT NULL COMMENT '商户id',
  `phone` varchar(30) NOT NULL COMMENT '手机号',
  `contact` varchar(30) NOT NULL COMMENT '联系人',
  `unionid` varchar(50) DEFAULT NULL COMMENT '微信开放平台唯一值',
  `service_number_openid` varchar(50) DEFAULT NULL COMMENT '服务号openid',
  `type` int(4) DEFAULT '1' COMMENT '类型(1:店主;2:店员;)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='商户人员表';

DROP TABLE IF EXISTS `merchant_order`;
CREATE TABLE `merchant_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchant_id` bigint(20) NOT NULL COMMENT '商户ID',
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `money` double(16,2) DEFAULT '0.00' COMMENT '该订单可获得金额',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `status` int(4) DEFAULT '0' COMMENT '状态(0:未处理;1:统一;2:驳回;)',
  `reason` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '驳回理由',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='商户订单表';

DROP TABLE IF EXISTS `merchant_commission`;
CREATE TABLE `merchant_commission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchant_id` bigint(20) NOT NULL COMMENT '商户id',
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `merchant_withdraw_id` bigint(20) DEFAULT NULL COMMENT '商户提现记录id',
  `commission` decimal(18,2) DEFAULT '0.00' COMMENT '佣金',
  `status` int(4) DEFAULT '0' COMMENT '状态(0:未成功;1:可提现;2:提现中;3:已提现;)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='商户佣金记录';

DROP TABLE IF EXISTS `merchant_withdraw`;
CREATE TABLE `merchant_withdraw` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchant_id` bigint(20) NOT NULL COMMENT '商户id',
  `money` decimal(18,2) DEFAULT '0.00' COMMENT '提现金额',
  `status` int(4) DEFAULT '0' COMMENT '提现状态(0:待处理;1:处理中,2:已处理)',
  `form_id` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '通知用字段',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='商户提现记录表';


DROP TABLE IF EXISTS `order_pay`;
CREATE TABLE `order_pay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '商户id',
  `pay_type` int(2)  NOT NULL COMMENT '支付类型(1:支付宝;2:微信;)',
  `subject` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品名称',
  `body` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品描述',
  `amount` decimal(18,2) DEFAULT '0.00' COMMENT '支付金额',
  `out_trade_no` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '外部支付订单号',
  `internal_trade_no` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '内部支付订单号',
  `notify_url` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '通知地址',
  `openid` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'openid',
  `payment_type` int(2)  NOT NULL COMMENT '1:"全款"  2:"定金"  3:"尾款"',
  `payment_status` int(2)  NOT NULL COMMENT '0:"等待确认"  1:"成功"  2:"失败"',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='支付记录表';

