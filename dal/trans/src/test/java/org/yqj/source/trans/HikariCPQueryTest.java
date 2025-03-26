package org.yqj.source.trans;

import com.google.common.collect.ImmutableMap;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariConfigMXBean;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

/**
 * @author 10126730
 * Date: 2024/8/22 17:47
 * Description:
 */
@Slf4j
public class HikariCPQueryTest {

//    @Test
    public void testQuery() {
        System.setProperty("com.zaxxer.hikari.blockUntilFilled", "true");

        // config
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        config.setUsername("root");
        config.setPassword("root");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        config.setInitializationFailTimeout(10000);
        config.setPoolName("localConnectTest");
        config.setMaximumPoolSize(50);
        config.setMaxLifetime(600000);
        config.setConnectionTimeout(3000);
        config.setInitializationFailTimeout(10000);

        HikariDataSource ds = new HikariDataSource(config);

        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }
            System.out.println(resultSet.toString());

            String info = generatePoolInfo(ds);
            System.out.println(info);

            statement.close();
            connection.close();
        } catch (Exception e) {
            log.error("error gain connect :{}", e);
        }

        String info = generatePoolInfo(ds);
        System.out.println(info);
    }

    private static String generatePoolInfo(HikariDataSource dataSource) {
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
