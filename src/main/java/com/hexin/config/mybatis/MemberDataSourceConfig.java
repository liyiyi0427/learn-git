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

import javax.sql.DataSource;

/**
 * Created by tiejiuzhou on 2017/3/28.
 */
@Configuration
@EnableAutoConfiguration
@MapperScan(value = "com.hexin.mapper.member",sqlSessionTemplateRef = "memberSqlSessionTemplate")
public class MemberDataSourceConfig {
    private Logger logger = LoggerFactory.getLogger(MemberDataSourceConfig.class);

    @Value("${jndi.open}")
    private boolean isJndiOpen;
    /** * mybatis 配置路径 */
    private static String MYBATIS_CONFIG = "mybatis-config.xml";
    /** * mybatis mapper resource 路径  */
    private static String MAPPER_PATH = "mybatis/member/*.xml";
    private String typeAliasPackage = "com.hexin.domain.member";

//    @Bean(name = "member")
//    @ConfigurationProperties(prefix = "spring.datasource.member")
//    public DataSource createDataSource(){
//        if(isJndiOpen){
//            JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
//            return (org.apache.tomcat.jdbc.pool.DataSource)dataSourceLookup.getDataSource("java:comp/env/jdbc/member");
//        }
//        logger.debug("Configruing member  DataSource");
//        return new DataSource();
//    }

    @Bean(name = "member")
    @ConfigurationProperties(prefix = "spring.datasource.hexin6")
    public DataSource createDataSource(){
        if(isJndiOpen){
            JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
            return (org.apache.tomcat.jdbc.pool.DataSource)dataSourceLookup.getDataSource("java:comp/env/jdbc/member");
        }
        logger.debug("Configruing hexin6  DataSource");
        return new DruidDataSource();
    }

    @Bean(name = "memberSqlSessionFactory")
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("member") DataSource dataSource) throws Exception {
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

    @Bean(name = "memberTransactionManager")
    public DataSourceTransactionManager createTransactionManager(@Qualifier("member") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "memberSqlSessionTemplate")
    public SqlSessionTemplate createSqlSessionTemplate(@Qualifier("memberSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
