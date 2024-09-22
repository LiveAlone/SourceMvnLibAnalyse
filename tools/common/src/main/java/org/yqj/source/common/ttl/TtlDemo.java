package org.yqj.source.common.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 10126730
 * Date: 2024/9/18 20:04
 * Description:
 */
@Slf4j
public class TtlDemo {

    private static final TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();

    public static void main(String[] args) {
        context.set("yao");
        log.info("set thread local success");
        final Object captured = TransmittableThreadLocal.Transmitter.capture();
//        printMessage();
        printMessageAsync();
    }

    public static void printMessageAsync() {
        new Thread(() -> {
            String ctx = context.get();
            log.info("context in async is {}", ctx);
        }).start();
    }


    public static void printMessage() {
        log.info("context is {}", context.get());
    }
}
