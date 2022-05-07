package com.felipelima.springwebflux.domain

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString(includeNames = true, includeFields = true, ignoreNulls = true, includePackage = false)
class Product {

    Long id

    String brand

    String description

    String color

    BigDecimal price

    String status

}
