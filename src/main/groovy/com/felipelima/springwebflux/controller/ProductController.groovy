package com.felipelima.springwebflux.controller

import com.felipelima.springwebflux.domain.Product
import com.felipelima.springwebflux.resources.dto.ProductDto
import com.felipelima.springwebflux.service.ProductGetterService
import com.felipelima.springwebflux.service.ProductServiceImpl
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@Slf4j
@RestController
class ProductController {

    @Autowired
    private ProductServiceImpl productService

    @Autowired ProductGetterService productGetterService

    @PostMapping("/v1/products")
    @ResponseStatus(HttpStatus.CREATED)
    Mono<Product> create(@RequestBody Product product) {
        productService.save(product).doOnNext(p -> log.info("Create new product - {}", p))
    }

    @GetMapping('/v1/products/{productId}')
    @ResponseStatus(HttpStatus.OK)
    Mono<ProductDto> findOne(@PathVariable Long productId) {
        productGetterService.findOne(productId).doOnNext(p -> log.info("Handling GET /v1/products/${productId}"))
    }


}
