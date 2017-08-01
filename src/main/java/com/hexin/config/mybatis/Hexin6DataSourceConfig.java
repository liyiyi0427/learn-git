package com.hexin.config.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 *
 * hexin6数据库配置
 * Created by tiejiuzhou on 2017/3/28.
 */
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@MapperScan(value = "com.hexin.mapper.hexin6",sqlSessionTemplateRef = "hexin6SqlSessionTemplate")
public class Hexin6DataSourceConfig {
    private Logger logger = LoggerFactory.getLogger(Hexin6DataSourceConfig.class);


    @Value("${jndi.open}")
    private boolean isJndiOpen;

    /** * mybatis 配置路径 */
    private static String MYBATIS_CONFIG = "mybatis-config.xml";
    /** * mybatis mapper resource 路径  */
    private static String MAPPER_PATH = "mybatis/hexin6/*.xml";
    private String typeAliasPackage = "com.hexin.domain.hexin6";

    @Bean(name = "hexin6")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hexin6")
    public DataSource createDataSource(){
        if(isJndiOpen){
            JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
            return (org.apache.tomcat.jdbc.pool.DataSource)dataSourceLookup.getDataSource("java:comp/env/jdbc/hexin6");
        }
        logger.debug("Configruing hexin6  DataSource");
        return new DruidDataSource();
    }

    @Bean(name = "hexin6SqlSessionFactory")
    @Primary
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("hexin6") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        /** 设置mybatis configuration 扫描路径 */
        bean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
        /** 添加mapper 扫描路径 */
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + MAPPER_PATH;
        bean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
        bean.setTypeAliasesPackage(typeAliasPackage);
        return bean.getObject();
    }

    @Bean(name = "hexin6TransactionManager")
    @Primary
    public DataSourceTransactionManager createTransactionManager(@Qualifier("hexin6") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "hexin6SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate createSqlSessionTemplate(@Qualifier("hexin6SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
