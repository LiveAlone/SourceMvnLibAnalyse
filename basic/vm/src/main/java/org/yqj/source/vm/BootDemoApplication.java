package org.yqj.source.vm;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;

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
        List<Integer> ls = ImmutableList.of(1, 2);
        log.debug("ls info is {}", ls);
        log.info("starting boot demo application ...");

    }
}
