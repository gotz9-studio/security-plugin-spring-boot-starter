package com.gotz9.plugin.security.boot.storage;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@MapperScan(value = "com.github.gotz9.security.core.storage", sqlSessionFactoryRef = SecurityPluginStorageConfiguration.SECURITY_SQL_SESSION_FACTORY)
public class SecurityPluginStorageConfiguration {

    public static final String SECURITY_SQL_SESSION_FACTORY = "SECURITY_SQL_SESSION_FACTORY";

    @Bean(SECURITY_SQL_SESSION_FACTORY)
    @ConditionalOnMissingBean(name = SECURITY_SQL_SESSION_FACTORY)
    SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath*:com/gotz9/plugin/security/core/storage/mysql/*.xml"));

        return factory.getObject();
    }

}