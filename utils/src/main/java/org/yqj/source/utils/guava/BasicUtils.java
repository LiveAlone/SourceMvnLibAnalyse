package org.yqj.source.engine.guava;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Optional;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/7/20
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class BasicUtils {

    public static void main(String[] args) {

//        NullDemo();

//        PreconditionCheck();

//        ObjectsDemo();

//        StringDemo();
    }

    private static void StringDemo() {
        // Joiner
//        Joiner joiner = Joiner.on("; ").skipNulls();
//        log.info("joiner string : {}", joiner.join("Harry", null, "Ron", "Hermione"));
//        log.info("joiner list: {}", Joiner.on(",").join(Arrays.asList(1, 5, 7)));

        //Splitter
        Iterable<String> split = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("foo,bar,,   qux");
        log.info("split string : {}", split);


        log.info("charset is :{}", Charsets.UTF_8);
        log.info("strings is :{}", Strings.nullToEmpty(""));
    }

    private static void ObjectsDemo() {
//        System.out.println(Objects.equals("a", "a")); // returns true
//        log.info("objects hashcode :{}", Objects.hashCode(1, 2, 3, 4));

        // Returns "ClassName{x=1}"
        System.out.println(MoreObjects.toStringHelper("ClassName").add("x", 1).toString());
    }

    private static void PreconditionCheck() {
        // Preconditions
//        Preconditions.checkArgument(1 > 2, "error message");
//        Preconditions.checkState(1 > 2, "error message");
//        Preconditions.checkNotNull(null, "error message");
//        Preconditions.checkElementIndex(2, 2, "error message");
//        Preconditions.checkPositionIndex(3, 2, "error message");
        Preconditions.checkPositionIndexes(1, 2, 3);
    }

    private static void NullDemo() {

        // Optional
        Optional<Integer> possible = Optional.of(5);
//        log.info("possible is present :{}", possible.isPresent()); // returns true
//        log.info("possible value is :{}", possible.get()); // returns 5

//        log.info("empty optional :{}", Optional.empty()); // returns false

//        log.info("map get: {}", possible.map(i -> i + 5).get());
//        log.info("possible or second :{}", possible.or(Optional::empty));

        // MoreObjects
//        log.info("more objects null : {}", MoreObjects.firstNonNull(null, "second"));

        // Strings
        log.info("strings empty to null: {}", Strings.emptyToNull(""));
        log.info("{}", Strings.nullToEmpty(null));
        log.info("{}", Strings.isNullOrEmpty(""));
    }
}
