# SourceMvnLibAnalyse
基于 Maven项目Demo 演示平台

## 模版项目模块

1. empty ```java基础项目```
    
2. app ```spring-boot应用基础模版```
    
3. web ```spring-boot web应用基础模版```



## Java 基础类库模块

### basic java基础类库
  - basic 基础类库集合 
  - aop 拦截实现不同方式 1. java原生代理 2. asm字节码修改 3. cglib代理类生成

### pattern 设计模式
  - Pattern-Design 设计模式Demo模块

### utils 常用工具集合
  - cache 缓存 guava，Caffeine
  - guava 基础功能支持
  - pool ApachePool池化工具类
  - easy_excel excel 文档内容解析工具

### resilience4j 支持java熔断限流工具

### sentinel-client 服务限流熔断工具

### engine 模版规则引擎
  - alibaba QLExpress 基于QLExpress的规则引擎


## dal 数据持久化模块
    - redis数据持久化 jedis lettuce redisson 等
    - mybatis 数据持久化
    - trans spring-trans 事务提交支持


## Spring Boot 应用模块

### sp-core springCore核心模块，IOC, AOP, BOOT 启动过程。
### sp-cache springCache缓存模块，redis, ehcache, guava, caffeine 等缓存模块支持。
### sp-webflux springWebFlux模块，基于reactor的webflux模块支持。
### zk zookeeper 模块，基于curator的zookeeper客户端封装。
### actuator spring应用监控工具



## Spring Cloud 云部署模块
### cloud-feign-la, cloud-feign-lb feign支持服务和调用端
### cloud-config cloud配置中心管理

