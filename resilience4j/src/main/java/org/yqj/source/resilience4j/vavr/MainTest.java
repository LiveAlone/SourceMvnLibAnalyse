package org.yqj.source.resilience4j.vavr;

import io.vavr.Function0;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.concurrent.Future;
import lombok.extern.slf4j.Slf4j;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;


/**
 * Description:
 *
 * @author yaoqijun
 * @date 2020/10/12
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class MainTest {

    public static void main(String[] args) {
//        tupleExample();

//        funcTest();

//        valuesTest();

        match();
    }

    public static void match() {
        // 函数方式匹配
        int i = 1;
        String s = Match(i).of(
                Case($(1), "one"),
                Case($(2), "two"),
                Case($(), "?")
        );
        System.out.println(s);
    }
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

    public static void valuesTest() {
        // 1. 通过Option.ofNullable() 创建
////        Optional<String> maybeFoo = Optional.of("foo");
////        Optional<String> maybeFooBar = maybeFoo.map(s -> (String)null).map(s -> s.toUpperCase() + "bar");
//
//        Option<String> maybeFoo = Option.of("foo");
//        try {
//            maybeFoo.map(s -> (String)null)
//                    .map(s -> s.toUpperCase() + "bar");
//        } catch (NullPointerException e) {
//            log.error("exception", e);
//        }

        // 2. Try 异常包裹和打印
//        Try.of(() -> {throw new RuntimeException("test");}).getOrElse(null);
//        Object result = Try.of(() -> {throw new RuntimeException("test");})
//                .recover(x -> Match(x).of(
//                        Case($(instanceOf(RuntimeException.class)), t -> t),
//                        Case($(instanceOf(IndexOutOfBoundsException.class)), t -> t),
//                        Case($(instanceOf(IllegalArgumentException.class)), t -> t)
//                )).getOrElse(null);

        // 3. Lazy
//        Lazy<Double> lazy = Lazy.of(Math::random);
//        System.out.println(lazy.isEvaluated()); // = false
//        System.out.println(lazy.get());         // = 0.123 (random generated)
//        System.out.println(lazy.isEvaluated()); // = true
//        System.out.println(lazy.get());         // = 0.123 (memoized)
//        CharSequence chars = Lazy.val(() -> "Yay!", CharSequence.class);

        // 4. Future

        Future<String> future = Future.of(() -> "test");
    }

    public static void funcTest() {
        // 1. 高阶函数 curried
//        Function2<Integer, Integer, Integer> sum = Integer::sum;
//        Function1<Integer, Integer> add2 = sum.curried().apply(2);
//        System.out.println(add2.apply(4));

//         2. 接口函数转换
//        Function3<String, String, String, String> function3 = Function3.of(this::methodWhichAccepts3Parameters);

        // 3. 函数组合
//        Function1<Integer, Integer> plusOne = a -> a + 1;
//        Function1<Integer, Integer> multiplyByTwo = a -> a * 2;
////        Function1<Integer, Integer> add1AndMultiplyBy2 = plusOne.andThen(multiplyByTwo);
////        System.out.println(add1AndMultiplyBy2.apply(2));
//
//        Function1<Integer, Integer> add1AndMultiplyBy2 = multiplyByTwo.compose(plusOne);
//        System.out.println(add1AndMultiplyBy2.apply(2));

        // 4. lift 封装Optional
//        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;
//        Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);
//        Option<Integer> i1 = safeDivide.apply(1, 0);
//        System.out.println(i1.isEmpty());
//        Option<Integer> i2 = safeDivide.apply(4, 2);
//        System.out.println(i2.get());
//
//        // 类似对本地函数进行封装
////        Function2<Integer, Integer, Option<Integer>> sum = Function2.lift(this::sum);
////        Option<Integer> optionalResult = sum.apply(-1, 2);

        // 5. 偏函数
////        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
////        Function1<Integer, Integer> add2 = sum.apply(2);
////        System.out.println(add2.apply(4));
//
//        Function5<Integer, Integer, Integer, Integer, Integer, Integer> sum = (a, b, c, d, e) -> a + b + c + d + e;
//        Function2<Integer, Integer, Integer> add6 = sum.apply(2, 3, 1);
//        System.out.println(add6.apply(4, 3));

        // 5. 柯里化 - 返回多阶高阶函数
////        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
////        Function1<Integer, Integer> add2 = sum.curried().apply(2);
//
//        Function3<Integer, Integer, Integer, Integer> sum = (a, b, c) -> a + b + c;
//        final Function1<Integer, Function1<Integer, Integer>> add2 = sum.curried().apply(2);
//        System.out.println(add2.apply(3).apply(4));

        // 6. 内存缓存
        Function0<Double> hashCache = Function0.of(Math::random).memoized();
        System.out.println(hashCache.apply());
        System.out.println(hashCache.apply());
    }

    public static void tupleExample() {
        Tuple2 tuple2 = Tuple.of(1, 2);
        System.out.println(tuple2._1());
        System.out.println(tuple2._2());
    }
}
