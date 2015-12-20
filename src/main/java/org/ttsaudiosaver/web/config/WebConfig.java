package org.ttsaudiosaver.web.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "org.ttsaudiosaver.web")
public class WebConfig extends WebMvcConfigurerAdapter {
	
	private static final Logger logger = Logger.getLogger(WebConfig.class);
	
	private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/view/";
	private static final String VIEW_RESLVER_SUFFIX = ".jsp";
	
	private static final String JS_RESOURCE_LOCATION = "/WEB-INF/js/";
	private static final String JS_RESOURCE_URI_PATTERN = "/js/**";
	private static final String FONTS_RESOURCE_LOCATION = "/WEB-INF/fonts/";
	private static final String FONTS_RESOURCE_URI_PATTERN = "/fonts/**";
	private static final String CSS_RESOURCE_LOCATION = "/WEB-INF/css/";
	private static final String CSS_RESOURCE_URI_PATTERN = "/css/**";
	private static final String ASSETS_RESOURCE_LOCATION = "/WEB-INF/assets/";
	private static final String ASSETS_RESOURCE_URI_PATTERN = "/assets/**";
	
	private static final String HIBERNATE_DIALECT_PROP = "hibernate.dialect";
	private static final String HIBERNATE_SHOW_SQL_PROP = "hibernate.show_sql";
	private static final String HIBERNATE_FORMAT_SQL_PROP = "hibernate.format_sql";
	private static final String HIBERNATE_HBM2DDL_AUTO_PROP = "hibernate.hbm2ddl.auto";
	
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix(VIEW_RESOLVER_PREFIX);
		resolver.setSuffix(VIEW_RESLVER_SUFFIX);
		return resolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(new AuthenticationInterceptor());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(JS_RESOURCE_URI_PATTERN).addResourceLocations(JS_RESOURCE_LOCATION);
		registry.addResourceHandler(FONTS_RESOURCE_URI_PATTERN).addResourceLocations(FONTS_RESOURCE_LOCATION);
		registry.addResourceHandler(CSS_RESOURCE_URI_PATTERN).addResourceLocations(CSS_RESOURCE_LOCATION);
		registry.addResourceHandler(ASSETS_RESOURCE_URI_PATTERN).addResourceLocations(ASSETS_RESOURCE_LOCATION);
	}
	
	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] {"org.ttsaudiosaver.web.model"});
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DataSourceConfig.DRIVER_CLASS_NAME);
		dataSource.setUrl(DataSourceConfig.URL);
		dataSource.setUsername(DataSourceConfig.USERNAME);
		dataSource.setPassword(DataSourceConfig.PASSWORD);
		return dataSource;
	}
	
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(HIBERNATE_DIALECT_PROP, HibernateConfig.DIALECT);
        properties.put(HIBERNATE_SHOW_SQL_PROP, HibernateConfig.SHOW_SQL);
        properties.put(HIBERNATE_FORMAT_SQL_PROP, HibernateConfig.FORMAT_SQL);
        properties.put(HIBERNATE_HBM2DDL_AUTO_PROP, HibernateConfig.HBM2DDL_UPDATE);
        return properties;        
    }
    
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
    	logger.info("********************************************************************");
    	logger.info("Inside transactionManager method - sessionFactory:" + sessionFactory);
    	logger.info("********************************************************************");
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
     }
}