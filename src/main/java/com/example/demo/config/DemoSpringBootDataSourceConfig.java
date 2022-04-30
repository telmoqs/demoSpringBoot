package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "demoEntityManagerFactory", 
		transactionManagerRef = "demoTransactionManager", 
		basePackages = {"com.example.demo.repository" })
public class DemoSpringBootDataSourceConfig {

	@Primary
	@Bean(name = "demoDataSourceProperties")
	@ConfigurationProperties("spring.datasource.db-demo")
	public DataSourceProperties demoDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
	@Bean(name = "demoDataSource")
	@ConfigurationProperties("spring.datasource.db-demo")
	public DataSource demoDataSource(
			@Qualifier("demoDataSourceProperties") DataSourceProperties demoDataSourceProperties) {
		return demoDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Primary
	@Bean(name = "demoEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean demoEntityManagerFactory(EntityManagerFactoryBuilder demoEntityManagerFactoryBuilder,
			@Qualifier("demoDataSource") DataSource demoDataSource) {

		Map<String, String> demoJpaProperties = new HashMap<>();
		demoJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		demoJpaProperties.put("hibernate.hbm2ddl.auto", "update");
		demoJpaProperties.put("javax.persistence.validation.mode", "auto");
		return demoEntityManagerFactoryBuilder.dataSource(demoDataSource).packages("com.example.demo.model").persistenceUnit("demoDataSource").properties(demoJpaProperties).build();
	}

	@Primary
	@Bean(name = "demoTransactionManager")
	public PlatformTransactionManager demoTransactionManager(@Qualifier("demoEntityManagerFactory") EntityManagerFactory demoEntityManagerFactory) {
		return new JpaTransactionManager(demoEntityManagerFactory);
	}

}