package com.felipelima.springwebflux.service

import com.felipelima.springwebflux.domain.ProductResponse
import com.felipelima.springwebflux.repository.ProductRepository
import com.felipelima.springwebflux.resources.ProductFindResource
import com.felipelima.springwebflux.resources.dto.ProductDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

import java.util.function.Function

@Service
class ProductGetterService {

    @Autowired
    ProductRepository productRepository

    Mono<ProductDto> findOne(Long productId) {
        ProductFindResource resource = new ProductFindResource(
            productId: productId
        )
        Mono.just(resource)
            .flatMap(findByProductId)
            .flatMap(buildProductDetails)
    }

    Function<ProductFindResource, Mono<ProductFindResource>> findByProductId = {
        ProductFindResource resource ->
            productRepository.findByProductId(resource.productId).map({
                resource.product = it
                resource
            })
    }

    Function<ProductFindResource, Mono<ProductDto>> buildProductDetails = {
        ProductFindResource resource ->
            ProductDto productDto = new ProductDto()
            productDto.buildWith(resource.product)
            Mono.just(productDto)
    }

}
