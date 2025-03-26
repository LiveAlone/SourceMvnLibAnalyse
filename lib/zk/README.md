# zk 集群方式

## 准备

### 集群方式启动
通过 compose 启动一个 zk 集群，包含 3 个节点，一个 leader，两个 follower
    
    ```bash
    docker-compose -f compose.yml -p local-zk-cluster up -d

     # 通过network方式，容器命令方式执行
    docker run --name some-zookeeper --restart always --network local-zk-cluster_default -d zookeeper
    docker run -it --rm --network local-zk-cluster_default zookeeper zkCli.sh -server zoo1
    ```
暴露端口 2181, 2182, 2183

### 单点测试方式启动
通过 compose 启动一个 zk 单点，包含 1 个节点
    
    ```bash
    docker run --name local-zk-single --restart always -p 8080:8080 -p 2181:2181 -d zookeeper

    # 连接方式
    docker run -it --rm --link local-zk-single:zookeeper zookeeper zkCli.sh -server zookeeper
    ``` 

## 官网示例

### 服务集群监听
1. doc DataMonitor.java Executor.java 为官网示例代码，用于监听Znode节点数据，完成服务不同状态切换方式。

2. 通过 zkCli.sh 创建节点，触发监听事件

    ```bash
    # 创建节点，监听开始执行任务
    create /zk_test my_data

    # 修改节点，修改节点数据，输出
    set /zk_test junk

    # 删除节点，监听停止执行任务
    delete /zk_test
    ```
## 执行

### 提供多个进程模拟Crash执行过程
