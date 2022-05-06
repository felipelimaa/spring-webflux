package com.felipelima.springwebflux.service.backoffice

import com.felipelima.springwebflux.configuration.DataSourceProvider
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.core.scheduler.Scheduler

import java.util.concurrent.Callable

@Slf4j
@Component
class ProductRegister {

    @Autowired
    DataSourceProvider dataSourceProvider

    @Autowired
    @Qualifier("jdbcScheduler")
    Scheduler scheduler

    static final String INSERT = '''
        INSERT INTO Products (
            Brand,
            Description,
            Color,
            Price
        ) VALUES (
            :Brand,
            :Description,
            :Color,
            :Price
        )
    '''

    Mono<Long> registerProduct(String brand, String description, String color, BigDecimal price) {
        async {
            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSourceProvider.webFluxDataSource)

            def params = new MapSqlParameterSource('Brand', brand )
                    .addValue('Description', description)
                    .addValue('Color', color)
                    .addValue('Price', price)

            String[] arr = ["Products_Id"]
            KeyHolder keyHolder = new GeneratedKeyHolder()

            try {
                jdbcTemplate.update(INSERT, params, keyHolder, arr)
            } catch (Exception e) {
                log.error("Error to create a new product - {}", e)
            }

            keyHolder.key.longValue()
        }
    }

    private <T> Mono<T> async(Callable<T> callable) {
        return Mono.fromCallable(callable).publishOn(scheduler)
    }

}
