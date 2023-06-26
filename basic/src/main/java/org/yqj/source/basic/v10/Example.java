package org.yqj.source.basic.v10;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/6/26
 * Email: yaoqijunmail@foxmail.com
 */
public class Example {
    public static void main(String[] args) {
        System.out.println("java version 10");

        initVarDemo();
    }

    private static void initVarDemo(){
        // list
        var list = new ArrayList<>();
        list.add(1);
        list.add("contest");
        System.out.println(list.get(0));
        System.out.println(list.get(1));

        // map 这样用不好
        var map = new HashMap<>();
        map.put(5, new String("test map"));
        map.put(new String("all"), 123);
        System.out.println(map.get(5));
        System.out.println(map.get("all"));
    }
}
