package org.yqj.source.resilience4j.vavr;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.function.Consumer;


/**
 * Description:
 *
 * @author yaoqijun
 * @date 2020/10/12
 * Email: yaoqijunmail@foxmail.com
 */
public class MainTest {

//    public static void main(String[] args) {
//
//        tupleExample();
//
//        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
//        Function1<Integer, Integer> add2 = sum.curried().apply(2);
//        System.out.println(add2.apply(4));
//
//        Function0<Double> hashCache =
//                Function0.of(Math::random).memoized();
//        System.out.println(hashCache.apply());
//        System.out.println(hashCache.apply());
//
//        // 函数方式匹配
////        int i = 1;
////        String s = Match(i).of(
////                Case($(1), "one"),
////                Case($(2), "two"),
////                Case($(), "?"),
////        );
//
//    }
//
//    public static void tupleExample() {
//
//        Tuple2 tuple2 = Tuple.of(1, 2);
//        System.out.println(tuple2._1());
//        System.out.println(tuple2._2());
//
//    }
//
//    public static void sideEffect() {
//        List<Integer> ls = Lists.newArrayList(1, 2, 3);
//
//        Consumer<Integer> consumer = integer -> {
//            ls.add(123);
//            System.out.println(integer);
//        };
//        consumer.accept(123);
//        System.out.println(ls);
//    }
}
