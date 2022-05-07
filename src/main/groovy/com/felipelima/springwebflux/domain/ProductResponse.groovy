package com.felipelima.springwebflux.domain

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, ignoreNulls = true, includePackage = false)
class ProductResponse {

    Long id

    String brand

    String description

    String color

    BigDecimal price

    String status

    static ProductResponse buildResponse(Product product) {
        ProductResponse productResponse = new ProductResponse()
        productResponse.id = product.id
        productResponse.brand = product.brand
        productResponse.description = product.description
        productResponse.color = product.color
        productResponse.price = product.price
        productResponse.status = product.status
        productResponse
    }
}
