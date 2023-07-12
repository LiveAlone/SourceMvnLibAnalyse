package org.yqj.source.spcore.framework.event;

import lombok.Getter;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2021/6/7
 * Email: yaoqijunmail@foxmail.com
 */
public class OrangeEvent extends FruitEvent {

    @Getter
    private boolean sweet;

    public OrangeEvent(Object source, String color, boolean sweet) {
        super(source, color);
        this.sweet = sweet;
    }
}
