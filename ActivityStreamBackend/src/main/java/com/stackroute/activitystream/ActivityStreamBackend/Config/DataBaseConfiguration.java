package com.stackroute.activitystream.ActivityStreamBackend.Config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.stackroute.activitystream.ActivityStreamBackend")
public class DataBaseConfiguration {   
	    
	    @Bean(name = "dataSource")
		public DataSource getDataSource() {
		    BasicDataSource dataSource = new BasicDataSource();
		    dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		    dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		    dataSource.setUsername("vanu");
		    dataSource.setPassword("123456");
		 
		    return dataSource;
		}	
		
		@Autowired
		@Bean(name = "sessionFactory")
		public SessionFactory getSessionFactory(DataSource dataSource)
		{
			LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
			sessionBuilder.scanPackages("com.stackroute.activitystream.ActivityStreamBackend.Model");
			sessionBuilder.setProperty("hibernate.show_sql", "true");
			sessionBuilder.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
			sessionBuilder.setProperty("hibernate.hbm2ddl.auto", "update");
			sessionBuilder.setProperty("hibernate.lazy", "false");
			return sessionBuilder.buildSessionFactory();
		}
		
		@Autowired
		@Bean(name = "transactionManager")
		public HibernateTransactionManager getTransactionManager(
		        SessionFactory sessionFactory) {
		    HibernateTransactionManager transactionManager = new HibernateTransactionManager(
		            sessionFactory);	 
		    return transactionManager;
		}

}
