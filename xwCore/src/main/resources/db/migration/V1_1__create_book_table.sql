CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `user_id` int(11) NOT NULL COMMENT '用户Id',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '目标类型（1 商机 ）',
  `target_id` int(11) NOT NULL DEFAULT '0' COMMENT '目标Id',
  `reason` varchar(200) DEFAULT NULL COMMENT '原因',
  `description` varchar(300) DEFAULT NULL COMMENT '详情',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态（0 未处理，1 已处理，2 无效）',
  `handle_result` varchar(200) DEFAULT NULL COMMENT '处理结果',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `auditor` int(11) NOT NULL DEFAULT '0' COMMENT '审核人',
  `info_deal` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1:关闭信息;2:删除信息',
  `audit_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  `merchant_mobile` tinyint(4) NOT NULL DEFAULT '0' COMMENT '商家账号处理 0：不处理 1：处理',
  `publish_mobile` tinyint(4) NOT NULL DEFAULT '0' COMMENT '发布者账号处理 0：不处理 1：处理',
  PRIMARY KEY (`id`),
  KEY `accuse_user_id_index` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
