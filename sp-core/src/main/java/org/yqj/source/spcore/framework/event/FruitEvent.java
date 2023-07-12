package org.yqj.source.spcore.framework.event;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2021/6/7
 * Email: yaoqijunmail@foxmail.com
 */
@ToString(callSuper = true)
public class FruitEvent extends ApplicationEvent {

    @Getter
    private String color;

    public FruitEvent(Object source, String color) {
        super(source);
        this.color = color;
    }
}
