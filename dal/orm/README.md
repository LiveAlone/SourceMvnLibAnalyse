# ORM  支持多数据源配置

## 单数据源配置方式

基于 Spring-JDBC 的自动配置, 参考application.yml中 1单个数据原配置 

## 多数据源手动声明

1. Hikari 多数据源配置

    参考application.yml中 ```2多数据源配置。```
    参考 ```org.yqj.source.orm.config.DataSourceConfig``` 助理连接池参数注入方式

2. Druid 多数据源配置

    参考application.yml中 ```3 druid 多数据源```
    初始化 DataSource ```Druid: org.yqj.source.orm.config.DruidDataSourceConfig 创建的是 DruidDataSourceWrapper``` 注入连接池属性

3. baomidou 多数据源自动集成

    参考application.yml中 ```4 mybatis-plus 动态数据源配置```
    通过抽象 ```com.baomidou.dynamic.datasource.DynamicRoutingDataSource``` ThreadLocal上下文获取数据源链接，执行SQL


## 关联表数据查询

支持 lambda 链式方式关联查询，生成 DAL 数据对象查询结果
```xml
    <dependency>
        <groupId>com.github.yulichang</groupId>
        <artifactId>mybatis-plus-join-boot-starter</artifactId> <!-- MyBatis 联表查询 -->
    </dependency>
```
































