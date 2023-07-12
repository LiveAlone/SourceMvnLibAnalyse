package org.yqj.source.spcore.framework.context;

import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/1/10
 * Email: yaoqijunmail@foxmail.com
 */
//@Component
@Slf4j
public class ComponentA {

    @Resource
    @Getter
    private ComponentB componentB;

    static {
//        log.info("********** component a class is loading");
    }

//    private final ComponentB componentB;
//    public ComponentA(ComponentB componentB) {
//        this.componentB = componentB;
//    }

    public String name() {
        log.info("contain b name is :{}", componentB);
        return "ComponentA";
    }

}
