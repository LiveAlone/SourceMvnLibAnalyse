package org.yqj.source.pattern.abstractfactory;

import org.yqj.source.pattern.abstractfactory.arch.Army;
import org.yqj.source.pattern.abstractfactory.arch.Castle;
import org.yqj.source.pattern.abstractfactory.arch.King;

/**
 * Created by yaoqijun on 2017/6/8.
 */
public class FaKingdomFactory implements KingdomFactory{
    @Override
    public King createKing() {
        return () -> "this is fa king";
    }

    @Override
    public Castle createCastle() {
        return () -> "this is fa castle";
    }

    @Override
    public Army createArmy() {
        return () -> "this is fa army";
    }
}
