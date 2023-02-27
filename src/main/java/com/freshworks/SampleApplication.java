package com.freshworks;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.freshworks")
@EntityScan("com.freshworks")
@EnableJpaAuditing
public class SampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

    @Bean
    public HibernatePropertiesCustomizer configureStatementInspector() {
        return (properties) -> properties.put(AvailableSettings.STATEMENT_INSPECTOR, (StatementInspector) sql -> {
            if(sql.startsWith("update") || sql.startsWith("delete from"))
                return sql.toUpperCase();
            return sql;
        });
    }
}
