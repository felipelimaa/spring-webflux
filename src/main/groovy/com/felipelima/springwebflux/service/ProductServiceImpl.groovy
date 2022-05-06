package com.felipelima.springwebflux.service

import com.felipelima.springwebflux.domain.Product
import com.felipelima.springwebflux.domain.ProductResponse
import com.felipelima.springwebflux.repository.ProductRepository
import com.felipelima.springwebflux.service.backoffice.ProductRegister
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

import java.util.function.Function

@Slf4j
@Component
class ProductServiceImpl implements ProductService {


    @Autowired
    ProductRepository productRepository

    @Autowired
    ProductRegister productRegister

    @Override
    Mono<Product> save(Product product) {
        Mono.just(product)
            .flatMap(registerProduct)
            .flatMap(findProduct)
            .flatMap(handleSuccess)
            .log()
    }

    Function<Product, Mono<Product>> findProduct = { Product product ->
        productRepository.findByProductId(product.id)
            .map({
                Product productSaved ->
                    product = productSaved

                    product
            })
    }

    Function<Product, Mono<Product>> registerProduct = { Product product ->
        productRegister.registerProduct(
            product.brand,
            product.description,
            product.color,
            product.price
        ).map({ id ->
            product.id = id
            product
        })
    }

    Function<Product, Mono<Product>> handleSuccess = { Product product ->
        Mono.just(ProductResponse.buildResponse(product))
    }

}
