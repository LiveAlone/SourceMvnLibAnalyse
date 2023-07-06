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
@Component
@Slf4j
public class ComponentB {

    @Resource
    @Getter
    private ComponentA componentA;

    static {
//        log.info("************** component b class is loading");
    }

//    private final ComponentA componentA;
//    public ComponentB(ComponentA componentA) {
//        this.componentA = componentA;
//    }

    public String name() {
        log.info("container a info is :{}", componentA);
        return "ComponentB";
    }

}
