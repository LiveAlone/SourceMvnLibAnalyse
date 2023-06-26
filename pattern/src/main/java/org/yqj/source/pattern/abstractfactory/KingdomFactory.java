package org.yqj.source.pattern.abstractfactory;

import org.yqj.source.pattern.abstractfactory.arch.Army;
import org.yqj.source.pattern.abstractfactory.arch.Castle;
import org.yqj.source.pattern.abstractfactory.arch.King;

/**
 * Description: 抽象工厂
 *
 * @author yaoqijun
 * @date 2023/6/26
 * Email: yaoqijunmail@foxmail.com
 */
public interface KingdomFactory {

    King createKing();

    Castle createCastle();

    Army createArmy();
}
