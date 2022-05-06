package com.felipelima.springwebflux.controller

import com.felipelima.springwebflux.domain.Product
import com.felipelima.springwebflux.service.ProductServiceImpl
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@Slf4j
@RestController
@RequestMapping("/products")
class ProductController {

    @Autowired
    private ProductServiceImpl productService

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mono<Product> create(@RequestBody Product product) {


        return productService.save(product).doOnNext(p -> log.debug("Create new product - {}", p))
    }


}
