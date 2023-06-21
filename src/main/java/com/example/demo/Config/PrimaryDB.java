package com.example.demo.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager",
        basePackages = { "com.example.demo.Repositories" }
)
public class PrimaryDB {

    //if we create dataSource using below method or DataSourceBuilder.create().build() then in application.yml file databse url
    // we have to mention jdbc-url instead of url
//    @Bean(name="primaryDataSource")
//    @Primary
//    @ConfigurationProperties(prefix="spring.primary")
//    public DataSource primaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }

    @Autowired
    Environment env;

    @Bean(name="primaryDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.primary")
    public DataSource primaryDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("spring.primary.url"));
        dataSource.setUsername(env.getProperty("spring.primary.username"));
        dataSource.setPassword(env.getProperty("spring.primary.password"));
        dataSource.setDriverClassName(env.getProperty("spring.primary.driver-class-name"));
        return dataSource;
    }


    @Primary
    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                              @Qualifier("primaryDataSource") DataSource primaryDataSource) {
        return builder
                .dataSource(primaryDataSource)
                .packages("com.example.demo.Entities")
                .build();
    }

    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(
            @Qualifier("primaryEntityManagerFactory") EntityManagerFactory primaryEntityManagerFactory) {
        return new JpaTransactionManager(primaryEntityManagerFactory);
    }
}
