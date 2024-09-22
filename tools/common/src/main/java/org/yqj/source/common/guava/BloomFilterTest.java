package org.yqj.source.common.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2021/8/11
 * Email: yaoqijunmail@foxmail.com
 */
public class BloomFilterTest {

    public static void main(String[] args) {
        // 创建布隆过滤器对象
//        BloomFilter<Integer> filter = BloomFilter.create(
//                Funnels.integerFunnel(),
//                1500,
//                0.01);
        // 判断指定元素是否存在
//        System.out.println(filter.mightContain(1));
//        System.out.println(filter.mightContain(2));

//        // 将元素添加进布隆过滤器
//        filter.put(1);
//        filter.put(2);
//        System.out.println(filter.mightContain(1));
//        System.out.println(filter.mightContain(2));

        // 计算公式
        long numApproxElements = 100000000000L;
        double fpp = 0.01;
        long bitmapLength = (long) (-numApproxElements * Math.log(fpp) / (Math.log(2) * Math.log(2)));
        int numHashFunctions = Math.max(1, (int) Math.round((double) bitmapLength / numApproxElements * Math.log(2)));
        long arraySize = bitmapLength / 8 / 1024 / 1024;
        System.out.println("长度是 " + arraySize + " M 长度");
        System.out.println("函数数量 " + numHashFunctions);
    }
}
