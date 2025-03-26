package org.yqj.source.redisson;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/6/6
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class RedissonFirstTest {
//    @Test
    public void testQuery() {
        // 1. Create config object
        Config config = new Config();
//        config.useClusterServers().addNodeAddress("redis://127.0.0.1:7181");
//        config = Config.fromYAML(new File("config-file.yaml"));
        config.useSingleServer().setAddress("redis://localhost:6379");
        config.setCodec(new StringCodec());

        // 2. Create Redisson instance
        RedissonClient redisson = Redisson.create(config);

        // 3. map
        RMap<String, String> map = redisson.getMap("myMap");
        map.put("key1", "value1");
        map.put("key2", "value2");

        redisson.shutdown();
    }
}
