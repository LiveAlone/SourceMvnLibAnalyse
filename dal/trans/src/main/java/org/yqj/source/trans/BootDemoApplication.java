package org.yqj.source.trans;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/6/6
 * Email: yaoqijunmail@foxmail.com
 */
@SpringBootApplication
public class BootDemoApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(BootDemoApplication.class)
                .run(args);
    }
}
