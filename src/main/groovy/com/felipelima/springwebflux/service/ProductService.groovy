package com.felipelima.springwebflux.service

import com.felipelima.springwebflux.domain.Product
import reactor.core.publisher.Mono

interface ProductService {

    Mono<Product> save(Product product)

}
