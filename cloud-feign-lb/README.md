# Spring 微服务部署方式

## 1. 服务注册中心
基于Zookeeper 统一服务发现方式

    ```bash
    # 启动单点服务
    docker run --name local-zk-single --restart always -p 2181:2181 -d zookeeper

    # 连接方式
    docker run -it --rm --link local-zk-single:zookeeper zookeeper zkCli.sh -server zookeeper
    ```

## 2. 服务网管启动方式

    ```bash
    # 服务访问方式
    curl http://localhost:8080/server

    # 启动方式命令行, 注册执行列表
    java -jar .\target\cloud-feign-la-1.0-SNAPSHOT.jar --spring.application.name=first
    ```