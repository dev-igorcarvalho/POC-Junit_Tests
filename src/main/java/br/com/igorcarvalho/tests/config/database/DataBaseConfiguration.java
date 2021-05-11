package br.com.igorcarvalho.tests.config.database;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
@Getter
@Setter
@Slf4j
public class DataBaseConfiguration {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    @Profile("dev")
    @Bean
    public String testDatabaseConnection() {
        log.info(driverClassName);
        log.info(url);
        return "DB Connection to POSTGRES - DEV instance";
    }

    @Profile("test")
    @Bean
    public String prodDatabaseConnection() {
        log.info(driverClassName);
        log.info(url);
        return "DB Connection to H2 - TEST instance";
    }
}
