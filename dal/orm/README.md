# ORM  支持多数据源配置

## 单数据源配置方式
参考 application.yml中 1 单个数据原配置, 单个数据源 SpringStarter 自动配置

## 多数据源配置

1. 手动初始化 DataSourceConfig 多数据源配置. 

    不同数据源 Druid Hikari, 不同链接方式，池化参数配置方式。
    Druid: org.yqj.source.orm.config.DruidDataSourceConfig
    Hikari: org.yqj.source.orm.config.DataSourceConfig

2. 