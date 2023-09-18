package org.yqj.source.cloud.feignlb;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.yqj.source.cloud.feignlb.dto.BaseRequest;
import org.yqj.source.cloud.feignlb.dto.BaseResponse;
import org.yqj.source.cloud.feignlb.feign.LaServer;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/6/6
 * Email: yaoqijunmail@foxmail.com
 */
@Component
@Slf4j
public class RunCommandLine implements CommandLineRunner {

    @Value("${spring.application.name:none}")
    private String appName;

    @Autowired
    private LoadBalancerClient loadBalancer;

//    @Autowired
//    private DiscoveryClient discovery;

    @Resource
    private LaServer laServer;

    @Override
    public void run(String... args) throws Exception {

//        for (int i = 0; i < 10; i++) {
////            BaseResponse<String> resp = laServer.server(new BaseRequest((long) i, "yaoqijun"));
////            log.info("command line runner is resp: {}", resp);
//
////            BaseResponse<String> resp = laServer.info(String.valueOf(i));
////            log.info("command line runner is resp: {}", resp);
//        }

//        BaseResponse<String> response = laServer.timeoutCall(String.valueOf(1024));
//        log.info("command line runner is resp: {}", response);

        BaseResponse<String> response = laServer.fail(String.valueOf(1024));
        log.info("command line runner is resp: {}", response);
    }
}
