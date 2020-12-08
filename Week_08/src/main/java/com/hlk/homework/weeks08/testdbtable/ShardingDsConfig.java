package com.hlk.homework.weeks08.testdbtable;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


@Configuration
public class ShardingDsConfig {
    @Bean
    public DataSource shardingDataSource() throws IOException, SQLException {
        return YamlShardingSphereDataSourceFactory.createDataSource(new File(ShardingDsConfig.class.getResource("/META-INF/replica-query.yaml").getFile()));
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("shardingDataSource") DataSource shardingDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(shardingDataSource);
        return bean.getObject();
    }

}
