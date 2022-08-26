CREATE TABLE `task` (
  `task_id` varchar(255) NOT NULL COMMENT '定时任务id',
  `task_name` varchar(255) DEFAULT NULL COMMENT '定时任务名称',
  `task_desc` varchar(255) DEFAULT NULL COMMENT '定时任务描述',
  `task_cron` varchar(255) DEFAULT NULL COMMENT '定时任务Cron表达式',
  `task_status` int(1) DEFAULT NULL COMMENT '定时任务状态，0停用 1启用',
  `task_class` varchar(255) DEFAULT NULL COMMENT '定时任务的Runnable任务类完整路径',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update` varchar(100) DEFAULT NULL COMMENT '修改人',
  `create` varchar(100) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`task_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='动态定时任务表';