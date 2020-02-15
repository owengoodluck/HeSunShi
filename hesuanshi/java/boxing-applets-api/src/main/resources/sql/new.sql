DROP TABLE IF EXISTS `robot`;
CREATE TABLE `robot` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `label` varchar(255) NOT NULL COMMENT '标签',
  `question` text NOT NULL COMMENT '问题',
  `answer` text NOT NULL COMMENT '答案',
  `browse` bigint(20) DEFAULT '0' COMMENT '浏览次数',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='一问一答表';


DROP TABLE IF EXISTS `health_eating`;
CREATE TABLE `health_eating` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `icon` varchar(255) NOT NULL COMMENT '图标',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='饮食分类表';


DROP TABLE IF EXISTS `food`;
CREATE TABLE `food` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `health_id` bigint(20) NOT NULL COMMENT '归类id',
  `icon` varchar(255) NOT NULL COMMENT '图标',
  `image` varchar(255) NOT NULL COMMENT '图片',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `alias` varchar(255) DEFAULT NULL COMMENT '别名',
  `pregnant_notice` int(4) NOT NULL COMMENT '孕妇提醒(1:适宜;2:禁止;慎吃;)',
  `pregnant_comment` varchar(500) NOT NULL COMMENT '孕妇诠释',
  `puerpera_notice` int(4) NOT NULL COMMENT '产妇提醒(1:适宜;2:禁止;慎吃;)',
  `puerpera_comment` varchar(500) NOT NULL COMMENT '产妇诠释',
  `lactation_notice` int(4) NOT NULL COMMENT '哺乳提醒(1:适宜;2:禁止;慎吃;)',
  `lactation_comment` varchar(500) NOT NULL COMMENT '哺乳诠释',
  `baby_notice` int(4) NOT NULL COMMENT '宝宝提醒(1:适宜;2:禁止;慎吃;)',
  `baby_comment` varchar(500) NOT NULL COMMENT '宝宝诠释',
  `nutrition` varchar(500) DEFAULT NULL COMMENT '营养介绍',
  `strategy` varchar(500) DEFAULT NULL COMMENT '选购攻略',
  `taboo_ingredient` varchar(500) DEFAULT NULL COMMENT '营养成分',
  `friendly_tips` varchar(500) DEFAULT NULL COMMENT '友好提醒',
  `baby_notice_month` int(4) DEFAULT 0 COMMENT '宝宝月份提醒',
  `sort` int(4) DEFAULT 0 COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='食材表';



DROP TABLE IF EXISTS `key_word`;
CREATE TABLE `key_word` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='关键词表';


DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `content` varchar(500) NOT NULL COMMENT '内容',
  `status` int(4) DEFAULT 0 COMMENT '类型(0:不公布;1:公布;)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='问题表';


DROP TABLE IF EXISTS `question_file`;
CREATE TABLE `question_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `question_id` bigint(20) NOT NULL COMMENT '问题id',
  `resource` varchar(500) NOT NULL COMMENT '资源',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='问题附件表';


DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `question_id` bigint(20) NOT NULL COMMENT '问题id',
  `type` int(4) DEFAULT 1 COMMENT '类型(1:医师;2:用户;)',
  `content` varchar(500) NOT NULL COMMENT '内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='解答表';


DROP TABLE IF EXISTS `currency`;
CREATE TABLE `currency` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `name` varchar(50) NOT NULL COMMENT '内容',
  `quantity` int(4) DEFAULT 0 COMMENT '数量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='糖币消费记录表';

INSERT INTO `sys_menu` VALUES (48, '1', '能不能吃管理', 'foodManage:show', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (49, '48', '新增能不能吃', 'foodManage:add', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (50, '48', '修改能不能吃', 'foodManage:edit', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (51, '48', '删除能不能吃', 'foodManage:del', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (52, '1', '一问多答', 'questionManage:show', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (53, '52', '问题回复', 'questionManage:answer', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (54, '1', '爬虫管理', 'climbManage:show', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (55, '54', '删除爬虫数据', 'climbManage:del', NULL, '', 1);

DROP TABLE IF EXISTS `climb`;
CREATE TABLE `climb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(500) NOT NULL COMMENT '标题',
  `content` text NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='爬虫表';
--在分类表里新增一个图标字段
alter table classify add icon varchar(500) DEFAULT NULL COMMENT "图标";
---在百科表新增状态字段
alter table baike add status int(4) DEFAULT '0' COMMENT "状态(0:不公布;1:公布;)";
--在问题表中删除标题字段
alter table question  DROP title; 
alter table question add mode int(4) DEFAULT '0' COMMENT "状态(0:已读;1:未读;)";


DROP TABLE IF EXISTS `onetoone`;
CREATE TABLE `onetoone` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` text NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='一问一答爬虫表';

INSERT INTO `sys_menu` VALUES (59, '1', '一问一答爬虫', 'onetooneManage:show', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (60, '59', '一问一答爬虫详情', 'onetooneManage:edit', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (61, '59', '删除一问一答爬虫', 'onetooneManage:del', NULL, '', 1);


DROP TABLE IF EXISTS `rule`;
CREATE TABLE `rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `quantity` int(4) DEFAULT 0 COMMENT '数量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='糖币消费规则表';

INSERT INTO `sys_menu` VALUES (65, '1', '糖币消费规则', 'ruleManage:show', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (66, '65', '新增糖币消费规则', 'ruleManage:add', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (67, '65', '编辑糖币消费规则', 'ruleManage:edit', NULL, '', 1);
INSERT INTO `sys_menu` VALUES (68, '65', '删除糖币消费规则', 'ruleManage:del', NULL, '', 1);

alter table user add quantity int(4) DEFAULT '0' COMMENT "糖币值";
alter table user add source  varchar(50) DEFAULT NULL COMMENT "来源";
alter table user add recommenderId  bigint(20) DEFAULT NULL COMMENT "推荐人id";


DROP TABLE IF EXISTS `award`;
CREATE TABLE `award` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `icon` varchar(255) NOT NULL COMMENT '图标',
  `name` varchar(30) NOT NULL COMMENT '名称',
  `probability` decimal(4,3) DEFAULT 0.00 COMMENT '概率',
  `count` int(4) DEFAULT 0 COMMENT '数量',
  `prize` int(4) DEFAULT 0 COMMENT '是否中奖',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='奖品表';

DROP TABLE IF EXISTS `lottery`;
CREATE TABLE `lottery` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `award_id` bigint(20) NOT NULL COMMENT '奖品id',
  `status` int(4) DEFAULT 0 COMMENT '状态(0:未兑现;1:已兑现)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='中奖表';