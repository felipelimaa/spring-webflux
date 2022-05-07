package com.felipelima.springwebflux.resources

import com.felipelima.springwebflux.domain.Product
import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, ignoreNulls = true, includePackage = false)
class ProductResource {

    Long id

    String brand

    String description

    String color

    BigDecimal price

    String status

    static ProductResource buildResponse(Product product) {
        ProductResource productResource = new ProductResource()
        productResource.id = product.id
        productResource.brand = product.brand
        productResource.description = product.description
        productResource.color = product.color
        productResource.price = product.price
        productResource.status = product.status
        productResource
    }

}
