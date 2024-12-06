* 内存Jvm 限制参数

```
-XX:+UnlockExperimentalVMOptions -Xms20M -Xmx20M
```


* gc 日志输出参数

    1. ```-verbose:gc``` 打印GC 日志
    2. ```-Xloggc:${gclogpath}``` gc 日志文件位置
    3. ```-XX:+PrintGCDetails``` 打印日志详情
    4. ```-XX:+PrintGCDateStamps``` 系统时间打印出来
    5. ```-XX:+PrintGCApplicationStoppedTime``` 打印出来 STW 时间
    6. ```-XX:+PrintTenuringDistribution``` 对象分布
  
命令参数 注意jvm 版本
```
-XX:+UnlockExperimentalVMOptions -Xms16m -Xmx16m -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCApplicationStoppedTime -XX:+PrintTenuringDistribution -Xloggc:gc_%p.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=log/ -XX:ErrorFile=hs_error_pid%p.log -XX:-OmitStackTraceInFastThrow
```

* gc 内存

    1. ```-XX:+HeapDumpOnOutOfMemoryError``` OOM dump jvm 内存配置
    2. ```-XX:HeapDumpPath=heap.prof``` heap dump 文件路径, 输出内存结果
    

   