package org.yqj.source.pattern.abstractfactory;

/**
 * Created by yaoqijun on 2017/6/8.
 */
public class App {

    public static void main(String[] args) {
        KingdomFactory  faFacotry = new FaKingdomFactory();
        System.out.println(faFacotry.createKing().getDescription());

        KingdomFactory maFactory = new MaKongdomFactory();
        System.out.println(maFactory.createKing().getDescription());
    }

}
