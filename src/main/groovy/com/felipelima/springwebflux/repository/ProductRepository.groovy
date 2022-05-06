package com.felipelima.springwebflux.repository

import com.felipelima.springwebflux.configuration.DataSourceProvider
import com.felipelima.springwebflux.domain.Product
import com.felipelima.springwebflux.infrastructure.exceptions.NotFoundException
import com.felipelima.springwebflux.repository.rowmappers.ProductRowMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.core.scheduler.Scheduler

import java.util.concurrent.Callable

@Component
class ProductRepository {

    @Autowired
    DataSourceProvider dataSourceProvider

    @Autowired
    ProductRowMapper productRowMapper

    @Autowired
    @Qualifier("jdbcScheduler")
    Scheduler scheduler

    static String FIND_BY_ID = '''
        SELECT
           p.Products_Id,
           p.Brand,
           p.Description,
           p.Color,
           p.Price
        FROM
            Products p
        WHERE
            p.Products_Id = :productId 
    '''

    Mono<Product> findByProductId(Long productId) {
        async {
            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSourceProvider.webFluxDataSource)
            def params = [
                productId: productId
            ]

            try {
                jdbcTemplate.queryForObject(FIND_BY_ID, params, productRowMapper)
            } catch (Exception e) {
                throw new NotFoundException(e.message)
            }
        }
    }

    private <T> Mono<T> async(Callable<T> callable) {
        return Mono.fromCallable(callable).publishOn(scheduler)
    }


}