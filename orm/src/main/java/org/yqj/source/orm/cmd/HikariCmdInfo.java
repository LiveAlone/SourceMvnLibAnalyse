package org.yqj.source.orm.cmd;

import com.google.common.collect.ImmutableMap;
import com.zaxxer.hikari.HikariConfigMXBean;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/6/6
 * Email: yaoqijunmail@foxmail.com
 */
//@Component
@Slf4j
public class HikariCmdInfo implements CommandLineRunner {

    @Resource
    private DataSource ordersDataSource;

    @Resource
    private DataSource usersDataSource;

    @Override
    public void run(String... args) throws Exception {

        selectInfo(ordersDataSource);
        String info = generateHikariPoolInfo(ordersDataSource);
        System.out.println(info);

        selectInfo(usersDataSource);
        String info2 = generateHikariPoolInfo(usersDataSource);
        System.out.println(info2);
    }

    private static void selectInfo(DataSource dataSource) {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }
            System.out.println(resultSet.toString());
            statement.close();
            connection.close();
        } catch (Exception e) {
            log.error("error gain connect :{}", e);
        }
    }

    private static String generateHikariPoolInfo(DataSource ds) {
        if (!(ds instanceof HikariDataSource)) {
            return "not hikari data source";
        }
        HikariDataSource dataSource = (HikariDataSource) ds;
        HikariPoolMXBean poolMXBean = dataSource.getHikariPoolMXBean();
        Map<String, Integer> poolBean = ImmutableMap.of("active", poolMXBean.getActiveConnections(),
                "idle", poolMXBean.getIdleConnections(),
                "total", poolMXBean.getTotalConnections(),
                "threadWait", poolMXBean.getThreadsAwaitingConnection());
        HikariConfigMXBean hikariConfigMXBean = dataSource.getHikariConfigMXBean();
        Map<String, Long> configBean = ImmutableMap.of("maxNumSize", (long) hikariConfigMXBean.getMaximumPoolSize(),
                "minIdle", (long) hikariConfigMXBean.getMinimumIdle(),
                "idleTime", hikariConfigMXBean.getIdleTimeout(),
                "connectionTimeout", hikariConfigMXBean.getConnectionTimeout(),
                "validateTime", hikariConfigMXBean.getLeakDetectionThreshold());
        return ImmutableMap.of("poolBean", poolBean, "configBean", configBean).toString();
    }
}
