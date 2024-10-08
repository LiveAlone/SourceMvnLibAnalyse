package org.yqj.source.orm.cmd;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author 10126730
 * Date: 2024/8/27 20:40
 * Description:
 */
//@Component
@Slf4j
public class DataSourceCmd implements CommandLineRunner {

    @Resource
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        log.info("[run][获得数据源：{}]", dataSource.getClass());
    }
}
