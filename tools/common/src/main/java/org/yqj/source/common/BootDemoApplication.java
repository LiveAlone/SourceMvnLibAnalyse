package org.yqj.source.common;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/6/6
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class BootDemoApplication {
    public static void main(String[] args) {

        List<BigDecimal> bigDecimals = Lists.newArrayList(
                new BigDecimal("1123.0"),
                new BigDecimal("243112.0"),
                new BigDecimal("2352.0"),
                new BigDecimal("12.0"),
                new BigDecimal("122.0"),
                new BigDecimal("2.0")
        );
        bigDecimals.sort(BigDecimal::compareTo);
        System.out.println(bigDecimals);
    }
}
