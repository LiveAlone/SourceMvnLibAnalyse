package org.yqj.source.lettuce.cmd;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.yqj.source.lettuce.entity.Person;

/**
 * @author 10126730
 * Date: 2024/8/26 16:01
 * Description:
 */
//@Component
@Slf4j
public class TemplateCmd implements CommandLineRunner {

    @Resource
    private RedisTemplate<String, Person> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void run(String... args) throws Exception {
        // 1 redis template
        redisTemplate.opsForList().leftPush("yqj-list", new Person("y", 1));
        redisTemplate.opsForList().leftPush("yqj-list", new Person("q", 2));
        redisTemplate.opsForList().leftPush("yqj-list", new Person("j", 3));
        log.info("left pop all :{} ", redisTemplate.opsForList().leftPop("yqj-list", Long.MAX_VALUE));

        // 2 string redis template
//        stringRedisTemplate.opsForList().leftPush("yqj-list", "y");
//        stringRedisTemplate.opsForList().leftPush("yqj-list", "q");
//        stringRedisTemplate.opsForList().leftPush("yqj-list", "j");
//        log.info("left pop all :{} ", stringRedisTemplate.opsForList().leftPop("yqj-list", Long.MAX_VALUE));

        // 3 hash Map
        redisTemplate.opsForHash().put("yqj-hash", "y", new Person("y", 1));
        redisTemplate.opsForHash().put("yqj-hash", "q", new Person("q", 2));
        redisTemplate.opsForHash().put("yqj-hash", "j", new Person("j", 3));
        log.info("hash get :{} ", redisTemplate.opsForHash().get("yqj-hash", "y"));
    }
}
