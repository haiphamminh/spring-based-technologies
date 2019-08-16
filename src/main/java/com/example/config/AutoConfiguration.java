package com.example.config;

import org.springframework.context.annotation.Configuration;

@Configuration
/*@EnableJpaRepositories(basePackages = "com.example.model",
                       entityManagerFactoryRef = "userEntityManager",
                       transactionManagerRef = "userTransactionManager")
@EnableTransactionManagement*/
//@EnableConfigurationProperties(Settings.class)
public class AutoConfiguration {
 /*   @Autowired
    private Settings settings;*/

   /* @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean userEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(userDataSource());
        em.setPackagesToScan(new String[]{"com.example.model"});
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        return em;
    }*/

  /*  @Bean
    @Primary
    public DataSource userDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(settings.getDriverClassName());
        dataSource.setUrl(settings.getUrl());
        return dataSource;
    }*/

    /*@Bean
    @Primary
    public PlatformTransactionManager userTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(userEntityManager().getObject());
        return transactionManager;
    }*/
}
