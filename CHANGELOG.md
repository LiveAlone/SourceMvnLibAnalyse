# Change Log


### 2024.06.11

- 添加 CHANGELOG.md 记录迁移变化
- 添加ORM模块。 DataSource, starter-jdbc, mybatis, mybatis-plus 支持业务框架。
- 添加doc模块，基于 Swagger SpringDoc 依赖支持 openapi3 协议格式。 支持不同 ui 接口展示框架。

### 2024.06.13

- session web 请求会话内容管理
- oauth 基于spring security构建可用的oauth2.0认证服务, spring历史版本

### 2024.09.11
- dal 逻辑修改测试
- tools, 封装常用工具类，简化业务代码

### 2024.12.06

spring 版本升级
<version>3.4.0</version>

sdk版本依赖升级，lombok插件支持配置
```xml
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
```

basic 模块分离
- lang 语言语法模块
- vm 虚拟机模块

JvmInitParamsTest github 项目删除
