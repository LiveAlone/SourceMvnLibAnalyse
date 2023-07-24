package org.yqj.source.utils.guava;

import com.google.common.collect.BiMap;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.MutableClassToInstanceMap;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.RangeSet;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Table;
import com.google.common.collect.TreeRangeMap;
import com.google.common.collect.TreeRangeSet;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Description: 集合数据类型演示
 *
 * @author yaoqijun
 * @date 2023/7/22
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class CollectionDemo {
    public static void main(String[] args) {
//        ImmutableCollection();

//        MultiCollection();

        CollectionUtils();
    }

    // 集合工具类
    private static void CollectionUtils(){

    }

    private static void MultiCollection() {
        // creates a ListMultimap with tree keys and array list values
//        ListMultimap<String, Integer> treeListMultimap =
//                MultimapBuilder.treeKeys().arrayListValues().build();
//        treeListMultimap.put("foo", 1);
//        treeListMultimap.put("foo", 2);
//        treeListMultimap.get("foo").forEach(value -> log.info("value is :{}", value));


        // bitmap demo
//        BiMap<String, Integer> biMap = HashBiMap.create(10);
//        biMap.put("foo", 1);
//        biMap.put("bar", 2);
//        biMap.put("baz", 3);
//        biMap.put("qux", 4);
////        biMap.put("kkk", 1);
//        biMap.forcePut("kkk", 1);
//        log.info("biMap revert get :{}", biMap.inverse().get(1));
//        log.info("biMap get :{}", biMap.get("foo"));

        // Table
//        Vertex v1 = new Vertex("v1");
//        Vertex v2 = new Vertex("v2");
//        Vertex v3 = new Vertex("v3");
//        Table<Vertex, Vertex, Integer> weightedGraph = HashBasedTable.create();
//        weightedGraph.put(v1, v2, 4);
//        weightedGraph.put(v1, v3, 20);
//        weightedGraph.put(v2, v3, 5);
//
//        log.info("row 1 :{}", weightedGraph.row(v1)); // returns a Map mapping v2 to 4, v3 to 20
//        log.info("column 3 :{}", weightedGraph.column(v3)); // returns a Map mapping v1 to 20, v2 to 5

        // put instance
        ClassToInstanceMap<Number> numberDefaults = MutableClassToInstanceMap.create();
        numberDefaults.putInstance(Integer.class, Integer.valueOf(0));
        numberDefaults.getInstance(Integer.class);

        // range set
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10)); // {[1, 10]}
        rangeSet.add(Range.closedOpen(11, 15)); // disconnected range: {[1, 10], [11, 15)}
        rangeSet.add(Range.closedOpen(15, 20)); // connected range; {[1, 10], [11, 20)}
        rangeSet.add(Range.openClosed(0, 0)); // empty range; {[1, 10], [11, 20)}
        rangeSet.remove(Range.open(5, 10)); // splits [1, 10]; {[1, 5], [10, 10], [11, 20)}
        log.info("range set is :{}", rangeSet);

        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "foo"); // {[1, 10] => "foo"}
        rangeMap.put(Range.open(3, 6), "bar"); // {[1, 3] => "foo", (3, 6) => "bar", [6, 10] => "foo"}
        rangeMap.put(Range.open(10, 20), "foo"); // {[1, 3] => "foo", (3, 6) => "bar", [6, 10] => "foo", (10, 20) => "foo"}
        rangeMap.remove(Range.closed(5, 11)); // {[1, 3] => "foo", (3, 5) => "bar", (11, 20) => "foo"}
        log.info("range map is :{}", rangeMap);
    }

    @ToString
    private static class Vertex {
        private String name;

        public Vertex(String name) {
            this.name = name;
        }
    }

    private static void ImmutableCollection() {
        ImmutableSet<String> COLOR_NAMES = ImmutableSet.of("red",
                "orange",
                "yellow",
                "green",
                "blue",
                "purple");
//        COLOR_NAMES.forEach(name -> log.info("color name is :{}", name));

//        ImmutableSet.builder().add("orange2")
//                .addAll(COLOR_NAMES)
//                .build()
//                .forEach(name -> log.info("color name is :{}", name));

//        ImmutableSet.copyOf(COLOR_NAMES).forEach(name -> log.info("color name is :{}", name));

        log.info("asList gain first :{},", COLOR_NAMES.asList().get(1));
    }
}
