Spring Boot Demo Application

Spring mybatis 支持3 注解相关的配置方式

Spring Transaction config 配置启动方式

Spring 多 事务， mybatis 结合执行方式

创建数据库启动
```sql
create database db1;

create database db2;
```

数据表的创建方式
```sql
CREATE TABLE `t_person` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(512) NOT NULL COMMENT '姓名',
	`age` INT(11) NOT NULL COMMENT '年龄',
	`score` DECIMAL(22,6) NOT NULL COMMENT '分数',
	PRIMARY KEY (`id`)
)
COMMENT='person'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
```
sql 创建配置方式
```sql
insert into t_person(`name`, `age`, `score`) values ('yao', 10, 66.6);
insert into t_person(`name`, `age`, `score`) values ('qi', 11, 66.7);
insert into t_person(`name`, `age`, `score`) values ('jun', 12, 66.8);
insert into t_person(`name`, `age`, `score`) values ('yao', 13, 66.6);
insert into t_person(`name`, `age`, `score`) values ('yao', 14, 66.6);
```
