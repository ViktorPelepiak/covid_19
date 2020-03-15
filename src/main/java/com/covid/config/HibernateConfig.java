package com.covid.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@PropertySource("classpath:database.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.covid.*", entityManagerFactoryRef = "getSessionFactory")
public class HibernateConfig {
    private static final String ENTITY_PACKAGE = "hibernate.entity.package";

    private final Environment environment;

    @Autowired
    public HibernateConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource getDataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(environment.getProperty(DRIVER));
        } catch (PropertyVetoException e) {
            System.exit(1);
        }
        dataSource.setJdbcUrl(environment.getProperty(URL));
        dataSource.setUser(environment.getProperty(USER));
        dataSource.setPassword(environment.getProperty(PASS));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(getDataSource());
        Properties properties = new Properties();

        properties.put(SHOW_SQL, environment.getProperty(SHOW_SQL));
        properties.put(HBM2DDL_AUTO, environment.getProperty(HBM2DDL_AUTO));
        properties.put(DIALECT, environment.getProperty(DIALECT));

        properties.put(C3P0_MIN_SIZE, environment.getProperty(C3P0_MIN_SIZE));
        properties.put(C3P0_MAX_SIZE, environment.getProperty(C3P0_MAX_SIZE));
        properties.put(C3P0_ACQUIRE_INCREMENT, environment.getProperty(C3P0_ACQUIRE_INCREMENT));
        properties.put(C3P0_TIMEOUT, environment.getProperty(C3P0_TIMEOUT));
        properties.put(C3P0_MAX_STATEMENTS, environment.getProperty(C3P0_MAX_STATEMENTS));

        sessionFactoryBean.setHibernateProperties(properties);
        sessionFactoryBean.setPackagesToScan(environment.getProperty(ENTITY_PACKAGE));

        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}
