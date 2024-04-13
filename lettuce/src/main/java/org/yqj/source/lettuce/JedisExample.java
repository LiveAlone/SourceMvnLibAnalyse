package org.yqj.source.lettuce;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/9/28
 */
@Slf4j
public class JedisExample {
    public static void main(String[] args) {
        log.info("jedis example");

//        bitBasicTest();

//        setTest();

        firstTest();
    }

    public static void firstTest() {
        Jedis jedis = new Jedis("localhost", 6379);
        for (int i = 0; i < 100; i++) {
            jedis.zadd("testSet", i, String.valueOf(i));
        }
        System.out.println(jedis.zrank("testSet", "1024"));
        System.out.println(jedis.zrank("testSet", "66"));
        System.out.println(jedis.zrank("testSet", "100"));
        System.out.println(jedis.zrank("testSet", "0"));
    }

    public static void setTest() {
        Jedis jedis = new Jedis("localhost", 6379);

//        String result = jedis.set("testSetSpecial", "1", "NX", "EX", 100);
//        System.out.println(result);
//        System.out.println(result == null);

//        String result = jedis.setex("testSetSpecial", 150, "123");
        String result = jedis.set("testSetSpecial", "66666666666");
        System.out.println(result);
    }

    public static void bitBasicTest() {
        String key = "bitNameKey";
        Jedis jedis = new Jedis("localhost", 6379);
//        System.out.println(jedis.setbit(key, 0, true));
//        for (byte aByte : jedis.get(key).getBytes()) {
//            System.out.println(aByte);
//        }
//        System.out.println(jedis.getbit(key, 2));
//        System.out.println(jedis.bitpos(key, true));
//        System.out.println(jedis.bitcount(key));

//        byte first = jedis.get(key.getBytes())[0];
//        byte pos1 = (byte) 0x80;
//        byte pos3 = (byte) 0x20;
//        System.out.println(pos1);
//        System.out.println(pos3);
//        System.out.println(pos1 & first);
//        System.out.println(pos3 & first);
//        System.out.println(ByteUtil.listPost(jedis.get(key.getBytes())));
    }
}
