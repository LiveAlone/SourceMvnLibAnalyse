package org.yqj.source.basic.base.collection;

import java.util.Iterator;

/**
 * @author yaoqijun on 2017-12-19.
 */
public class IterableDemo {

    public static void main(String[] args) {
        MyList myList = new MyList();
//        for (Integer integer : myList) {
//            System.out.println(integer);
//        }

        myList.forEach(System.out::println);
    }


    private static class MyList implements Iterable<Integer>{
        Integer ps = 0;

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                @Override
                public boolean hasNext() {
                    return ps < 100;
                }

                @Override
                public Integer next() {
                    ps = ps + 1;
                    return ps;
                }
            };
        }
    }
}
