
#### sentinel dashboard 服务构建工具

镜像支持自己构建
```shell

docker build -t yaoqijun/sentinel-dashboard:1.0.0 . // 构建版本工具

docker run --name sentinel-dashboard -p 8090:8090 -d yaoqijun/sentinel-dashboard:1.0.0

```
构建完成 [yaoqijun/sentinel-dashboard](https://hub.docker.com/repository/docker/yaoqijun/sentinel-dashboard) 地址