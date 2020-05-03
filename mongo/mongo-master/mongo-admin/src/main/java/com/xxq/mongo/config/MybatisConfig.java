package com.xxq.mongo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 23:54 2020/4/11
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
//@Configuration
//@MapperScan("com.xxq.mongo.**.mapper")//扫描dao包下的mapper
public class MybatisConfig {
    @Autowired
    private DataSource dataource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataource);
        //扫描model
        sessionFactory.setTypeAliasesPackage("com.xxq.mongo.**.entity");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //扫描映射文件
        sessionFactory.setMapperLocations(resolver.getResources("classpath*:/mapper/*.xml"));
        return sessionFactory.getObject();
    }
}
