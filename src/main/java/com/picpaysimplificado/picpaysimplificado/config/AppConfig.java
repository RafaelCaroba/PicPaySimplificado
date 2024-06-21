package com.picpaysimplificado.picpaysimplificado.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/picpaysimple");
        dataSource.setUsername("root");
        dataSource.setPassword("admin");
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter =new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");
        adapter.setShowSql(true);
        adapter.setPrepareConnection(true);
        adapter.setGenerateDdl(true);
        return adapter;
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public RestClient restClient(){
        return new RestClient() {
            @Override
            public RequestHeadersUriSpec<?> get() {
                return null;
            }

            @Override
            public RequestHeadersUriSpec<?> head() {
                return null;
            }

            @Override
            public RequestBodyUriSpec post() {
                return null;
            }

            @Override
            public RequestBodyUriSpec put() {
                return null;
            }

            @Override
            public RequestBodyUriSpec patch() {
                return null;
            }

            @Override
            public RequestHeadersUriSpec<?> delete() {
                return null;
            }

            @Override
            public RequestHeadersUriSpec<?> options() {
                return null;
            }

            @Override
            public RequestBodyUriSpec method(HttpMethod method) {
                return null;
            }

            @Override
            public Builder mutate() {
                return null;
            }
        };
    }
}
