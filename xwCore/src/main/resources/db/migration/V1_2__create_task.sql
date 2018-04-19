CREATE TABLE `task_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `biz_code` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '""' COMMENT '业务code',
  `full_class` varchar(400) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '""',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `corn` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '""' COMMENT 'corn表达式',
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '""' COMMENT '名称',
  `group_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '""' COMMENT '所属组',
  `trigger_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '""',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0:正常运行;1:删除;2:停止运行',
  PRIMARY KEY (`id`),
  KEY `idx1` (`biz_code`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci