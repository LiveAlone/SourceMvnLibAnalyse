package org.yqj.source.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author 10126730
 * Date: 2024/8/23 17:55
 * Description:
 */
@Slf4j
public class LettuceConnectTest {

    @Test
    public void pfCountRate() {
        RedisClient redisClient = RedisClient.create("redis://localhost/0");
        StatefulRedisConnection<String, String> stringStringStatefulRedisConnection = redisClient.connect();
        RedisCommands<String, String> redisCommands = stringStringStatefulRedisConnection.sync();

        ThreadLocalRandom random = ThreadLocalRandom.current();
        String pfKey = "pfTestKey1";
        int diff = 0;
        for (int i = 0; i < 10000; i++) {
            long change = redisCommands.pfadd(pfKey, String.format("%d_%d", random.nextInt(10000000), i));
            diff += change;
            System.out.println("current pos " + i + "  diff is " + diff);
        }
        long totalCount = redisCommands.pfcount(pfKey);
        System.out.println("total count 10000, diff count " + diff + " query count : " + totalCount);
    }

    @Test
    public void keysScan() throws Exception {
        RedisClient redisClient = RedisClient.create("redis://localhost/0");
        StatefulRedisConnection<String, String> stringStringStatefulRedisConnection = redisClient.connect();
        RedisCommands<String, String> redisCommands = stringStringStatefulRedisConnection.sync();
        List<String> keys = redisCommands.keys("*");
        keys.forEach(key -> {
            log.info("redis key gain from key:{}, val ", key);
//            log.info("redis key gain from key:{}, value:{} ", key, redisCommands.get(key));
        });
    }

    @Test
    public void getRedisKey() throws Exception {
        RedisClient redisClient = RedisClient.create("redis://localhost/0");
        StatefulRedisConnection<String, String> stringStringStatefulRedisConnection = redisClient.connect();
        RedisCommands<String, String> redisCommands = stringStringStatefulRedisConnection.sync();
        redisCommands.set("key", "keyValue");
        String result = redisCommands.get("key");
        System.out.println(result);

        for (int a = 0; a < 1000; a++) {
            redisCommands.set("key" + String.valueOf(a), "yaoqijun");
        }

        stringStringStatefulRedisConnection.close();
        redisClient.shutdown();
    }

    @Test
    public void getKeyReactiveTask() throws Exception {
        RedisClient redisClient = RedisClient.create("redis://localhost/0");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        log.info("**** start");

//        connection.reactive().get("key").subscribe(s->{
//            System.out.println("****** get key value is " + s);
//            System.out.println("current thread is " + Thread.currentThread().getName());
//        });
//        Thread.sleep(2000);

//        connection.reactive().get("key").map(s -> {
//                    System.out.println(String.format("v1-%s-%s", s, Thread.currentThread().getName()));
//                    return s;
//                }).publishOn(Schedulers.parallel()).map(s -> {
//                    System.out.println(String.format("v2-%s-%s", s, Thread.currentThread().getName()));
//                    return s;
//                }).subscribe(System.out::println);
//        Thread.sleep(2000);

        connection.reactive().set("key2", "Hello world").block();
        Mono<String> mono = connection.reactive().get("key2");
        mono.subscribe(s -> {
            System.out.println("****** get key value is " + s);
            System.out.println("current thread is " + Thread.currentThread().getName());
        });
        log.info("**** all finish: {}", mono.block());
        connection.close();
        redisClient.shutdown();
    }

    @Test
    public void getRunTask() throws Exception {
        RedisClient redisClient = RedisClient.create("redis://localhost/0");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        log.info("**** start");
        // event loop 线程名称
        RedisFuture redisFuture = connection.async().get("key");
        redisFuture.thenAccept(s -> {
            System.out.println("current thread is " + Thread.currentThread().getName());
            System.out.println("current s value is " + s);
        });
        log.info("**** all finish :{}", redisFuture.get());
        connection.close();
        redisClient.shutdown();
    }

    @Test
    public void readWriteExample() {
        RedisClient redisClient = RedisClient.create("redis://localhost/0");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        System.out.println("Connected to Redis");
        connection.sync().set("key", "Hello World");
        System.out.println(connection.sync().get("key"));
        connection.close();
        redisClient.shutdown();
    }

    @Test
    public void testPingPongRedis() {
        RedisClient redisClient = RedisClient.create("redis://localhost/0");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        System.out.println(connection.sync().zrem("test_zset"));
        connection.close();
        redisClient.shutdown();
    }
}
