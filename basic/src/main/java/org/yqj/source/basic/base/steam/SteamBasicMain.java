package org.yqj.source.basic.base.steam;

import com.google.common.collect.Lists;

/**
 * Description: basic steam
 *
 * @author yaoqijun
 * Date: 2021/12/13
 * Email: yaoqijunmail@foxmail.com
 */
public class SteamBasicMain {

    public static void main(String[] args) {
        basicTest();
    }

    public static void basicTest() {
        Lists.newArrayList(1, 2, 3, 4)
                .stream()
                .map(v -> {
                    System.out.println("convert to string is" + v);
                    return String.valueOf(v);
                })
                .forEach(System.out::println);

//        List<String> result = Lists.newArrayList(1, 2, 3, 4).stream()
//                .map(s -> s * 10)
//                .flatMap(p -> Stream.of(p, p / 2, p / 4, p / 8))
//                .map(String::valueOf).collect(Collectors.toList());
//        System.out.println(result);
    }
}