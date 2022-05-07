package com.felipelima.springwebflux.resources.dto

import com.felipelima.springwebflux.domain.Product

class ProductDto {

    Long id

    String brand

    String description

    String color

    BigDecimal price

    String status

    def buildWith(Product product) {
        this.id = product.id
        this.brand = product.brand
        this.description = product.description
        this.color = product.color
        this.price = product.price
        this.status = product.status
    }

}
