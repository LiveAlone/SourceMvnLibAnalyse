package org.yqj.source.zk.curator;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/8/28
 */
@Configuration
@Data
public class CuratorConfig {

    @Value("${zookeeper.connectionString}")
    private String connectionString = "localhost:2181";
}
