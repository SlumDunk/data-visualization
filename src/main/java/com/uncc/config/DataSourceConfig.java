package com.uncc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author: zerongliu
 * @Date: 10/30/18 10:35
 * @Description:
 */
@Configuration
@MapperScan(basePackages = "com.uncc.**.mapper")
@EnableTransactionManagement
public class DataSourceConfig {
    /**
     * env provided by spring
     */
    @Autowired
    private Environment env;

    /**
     * create data source
     *
     * @return
     * @throws SQLException
     */
    @Primary
    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("db_driverClassName"));
        dataSource.setUrl(env.getProperty("db_url"));
        dataSource.setUsername(env.getProperty("db_username"));
        dataSource.setPassword(env.getProperty("db_password"));
        dataSource.setPassword(
                env.getProperty("db_password"));
        dataSource.setInitialSize(Integer.parseInt(env.getProperty("db_initialSize")));
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("db_minIdle")));
        dataSource.setMaxActive(Integer.parseInt(env.getProperty("db_maxActive")));
        return dataSource;
    }

    /**
     * configure sql session factory
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        // get configuration information from properties
        String mapperPackage = env.getProperty("spring.mybatis.mapperLocations");
        String dialect = env.getProperty("spring.mybatis.dialect");
        Properties properties = new Properties();
        properties.setProperty("dialect", dialect);
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setConfigurationProperties(properties);
        Resource[] resources = null;
        // set the path of MapperLocations
        if (!StringUtils.isEmpty(mapperPackage)) {
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            resources = resourcePatternResolver.getResources(mapperPackage);
            sessionFactory.setMapperLocations(resources);
        }
        // create page interceptor
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties pageProperties = new Properties();
        pageProperties.setProperty("helperDialect", "postgresql");
        // page resonable argument，the default value is false 。when it is true，
        // when pageNum<=0,query the first page，pageNum>pages, query the last page. if it is false, query base on the argument。
        pageProperties.setProperty("reasonable", "true");
        // support to transfer page information on method arguments
        pageProperties.setProperty("supportMethodsArguments", "true");
        pageInterceptor.setProperties(pageProperties);
        // set the plugin
        sessionFactory.setPlugins(new Interceptor[]{pageInterceptor});

        SqlSessionFactory sqlSessionFactory = sessionFactory.getObject();
        return sqlSessionFactory;
    }

    /**
     * create sql session template
     *
     * @param sqlSessionFactory
     * @return
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * transaction manager
     *
     * @param dataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
