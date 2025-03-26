package org.yqj.source.trans;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author 10126730
 * Date: 2024/8/22 17:43
 * Description:
 */
@Slf4j
public class DruidQueryTest {

//    @Test
    public void testQuery() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setName("localTest");

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }
            System.out.println(resultSet.toString());

            generatePoolInfo(dataSource);

            statement.close();
            connection.close();
        } catch (Exception e) {
            log.error("error gain connect :{}", e);
        }

        generatePoolInfo(dataSource);
    }

    private static void generatePoolInfo(DruidDataSource dataSource) {
        System.out.println(dataSource.getStatDataForMBean());
    }
}
