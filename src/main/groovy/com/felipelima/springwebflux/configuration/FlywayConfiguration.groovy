package com.felipelima.springwebflux.configuration

import org.flywaydb.core.Flyway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

@Configuration
class FlywayConfiguration {

    @Autowired
    DataSourceProvider dataSourceProvider

    @Autowired
    Environment environment

    @Bean("flyway")
    Flyway flyway() {
        Flyway flyway = new Flyway()
        flyway.dataSource = dataSourceProvider.webFluxDataSource
        flyway.repair()
        flyway.migrate()
        flyway
    }

}
