package com.felipelima.springwebflux.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

import javax.sql.DataSource

@Component
class DataSourceProvider {

    @Autowired
    @Qualifier("webfluxDataSource")
    DataSource webfluxDataSource

    DataSource getWebFluxDataSource() {
        webfluxDataSource
    }

}
