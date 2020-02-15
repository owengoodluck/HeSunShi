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
  `reserve_num` int(4) DEFAULT 0 COMMENT '预留量　',
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
  `box_layout_classify_id` bigint(20) NOT NULL COMMENT '版面分类id',
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

DROP TABLE IF EXISTS `box_custom`;
CREATE TABLE `box_custom` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `type_image` varchar(255) NOT NULL COMMENT '盒型图片',
  `material_image` varchar(255) NOT NULL COMMENT '材质图片',
  `layout_image` varchar(255) NOT NULL COMMENT '版面图片',
  `length` int(4) DEFAULT 0 COMMENT '长',
  `width` int(4) DEFAULT 0 COMMENT '宽',
  `height` int(4) DEFAULT 0 COMMENT '高',
  `piece` int(4) DEFAULT 0 COMMENT '件数',
  `print_method` varchar(50) NOT NULL COMMENT '印刷方式',
  `remark` varchar(255) DEFAULT NULL COMMENT '买家留言',
  `status` int(4) DEFAULT 0 COMMENT '状态(0:未处理;1:已处理;)',
  `result` varchar(500) DEFAULT NULL COMMENT '处理结果',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='包装定制';

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

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `order_number` varchar(30) NOT NULL COMMENT '订单编号',
  `business_number` varchar(30) NOT NULL COMMENT '交易编号',
  `contact_name` varchar(30) NOT NULL COMMENT '买家姓名',
  `contact_phone` varchar(30) NOT NULL COMMENT '买家手机号',
  `contact_address` varchar(255) NOT NULL COMMENT '买家地址',
  `remark` varchar(255) DEFAULT NULL COMMENT '买家留言',
  `status` int(4) DEFAULT 0 COMMENT '状态(0:待付款;1:待发货;2:待收货;3:交易成功;4:交易取消;5:交易删除;)',
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
  `type` int(4) DEFAULT 1 COMMENT '类型(1:定制;2:现货;)',
  `specification_id` bigint(20) NOT NULL COMMENT '规格id',
  `price` decimal(18,2) DEFAULT '0.00' COMMENT '价格',
  `status` int(4) DEFAULT '0' COMMENT '状态(0:待完成;1:已完成;2:退款中;3:已退款)',
  `quantity` int(4) DEFAULT 0 COMMENT '数量',
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
  `type_id` bigint(20) NOT NULL COMMENT '盒型id',
  `material_id` bigint(20) NOT NULL COMMENT '材质id',
  `layout_id` bigint(20) NOT NULL COMMENT '版面id',
  `print_method_id` bigint(20) NOT NULL COMMENT '印刷方式id',
  `length` int(4) DEFAULT 0 COMMENT '长',
  `width` int(4) DEFAULT 0 COMMENT '宽',
  `height` int(4) DEFAULT 0 COMMENT '高',
  `piece` int(4) DEFAULT 0 COMMENT '件数',
  `remark` varchar(255) DEFAULT NULL COMMENT '买家留言',
  `price` decimal(18,2) DEFAULT '0.00' COMMENT '价格',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_upload` varchar(2) NOT NULL COMMENT '是否上传，0-未上传(默认)；1-已上传',
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

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '机构名称',
  `remark` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='机构表';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pid` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '菜单父id',
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '菜单名称',
  `power` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限',
  `ismenu` int(11) DEFAULT NULL COMMENT '是否是菜单（1：是  0：按钮）',
  `tips` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `status` int(65) DEFAULT NULL COMMENT '菜单状态 :  1:启用   0:不启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='菜单表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色全称',
  `empower` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限串,用于存放菜单ID(1,2,3)',
  `remark` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '真实姓名',
  `account` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '账号',
  `password` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `roleid` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色id',
  `deptid` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '机构id',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统用户表';

DROP TABLE IF EXISTS `notify_personnel`;
CREATE TABLE `notify_personnel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='通知人员表';

DROP TABLE IF EXISTS `promoter`;
CREATE TABLE `promoter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '名称',
  `real_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT '真实姓名',
  `phone` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT '手机号码',
  `content` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '资源优势',
  `status` int(4) DEFAULT '0' COMMENT '资格审查(0:未审核;1:审核通过;2: 不通过)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='推广人员表';

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `item_key` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '配置选项的名称',
  `item_value` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '配置选项的值',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='配置选项表';
