package org.yqj.source.spcore.framework.event;

import lombok.Getter;
import lombok.ToString;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2021/6/7
 * Email: yaoqijunmail@foxmail.com
 */
@ToString(callSuper = true)
public class AppleEvent extends FruitEvent {

    @Getter
    private String location;

    public AppleEvent(Object source, String color, String location) {
        super(source, color);
        this.location = location;
    }
}
