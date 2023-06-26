package org.yqj.source.pattern.adapter;

/**
 * Created by yaoqijun on 2017-06-09.
 */
public class BattleFishingBoat implements BattleShip{

    private FishingBoat fishingBoat;

    public BattleFishingBoat(){
        fishingBoat = new FishingBoat();
    }

    @Override
    public void move() {
        fishingBoat.sail();
    }

    @Override
    public void fire() {
        System.out.println("fire boat");
    }
}
