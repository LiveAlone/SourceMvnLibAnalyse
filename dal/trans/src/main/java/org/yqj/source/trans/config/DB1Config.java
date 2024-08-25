package org.yqj.source.trans.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author yaoqijun on 2018-03-12.
 */
@Configuration
@Slf4j
@MapperScan(value = "org.yqj.source.trans.db1",
        sqlSessionFactoryRef = DB1Config.DB1_SQL_SESSION_FACTORY,
        sqlSessionTemplateRef = DB1Config.DB1_SQL_SESSION_TEMPLATE)
public class DB1Config {

    public static final String DB1_DATASOURCE_NAME = "datasource_db1";

    public static final String DB1_TRANSACTION = "transaction_db1";

    public static final String DB1_SQL_SESSION_FACTORY = "sessionFactory_db1";

    public static final String DB1_SQL_SESSION_TEMPLATE = "sqlSessionTemplate_db1";

    @Bean(DB1_SQL_SESSION_TEMPLATE)
    public SqlSessionTemplate sqlSessionTemplate(){
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean(DB1_SQL_SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory(){
        try {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(dataSource());
            return bean.getObject();
        }catch (Exception e){
            log.error("error create sql session Factory", e);
            throw new IllegalStateException("");
        }
    }

    @Bean(DB1_TRANSACTION)
    public DataSourceTransactionManager dataSourceTransactionManager(){
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource(dataSource());
        return txManager;
    }

    @Bean(DB1_DATASOURCE_NAME)
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/db1?autoReconnect=true&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setMinIdle(10);
        dataSource.setMaxActive(50);
        dataSource.setMaxWait(30000);
        dataSource.setInitialSize(10);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(280); // sec
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000); //
        dataSource.setValidationQuery("SELECT 1 FROM DUAL");
        dataSource.setTestOnBorrow(true);
        return dataSource;
    }

}
