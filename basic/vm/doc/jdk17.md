* 17 运行命令行参数

```
-XX:+UnlockExperimentalVMOptions -Xms16m -Xmx16m -verbose:gc -Xlog:gc,gc+ref=debug,gc+heap=debug,gc+age=trace:file=gc_%p.log:tags,uptime,time,level -Xlog:safepoint:file=safepoint_%p.log:tags,uptime,time,level -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heap.hprof -XX:ErrorFile=hs_error_pid%p.log -XX:-OmitStackTraceInFastThrow
```