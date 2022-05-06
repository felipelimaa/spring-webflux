package com.felipelima.springwebflux.repository.rowmappers

import com.felipelima.springwebflux.domain.Product
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component

import java.sql.ResultSet
import java.sql.SQLException

@Component
class ProductRowMapper implements RowMapper<Product>{

    static final String PRODUCTS_ID = "Products_Id"
    static final String BRAND = "Brand"
    static final String DESCRIPTION = "Description"
    static final String COLOR = "Color"
    static final String PRICE = "Price"

    @Override
    Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product()
        product.id = resultSet.getLong(PRODUCTS_ID)
        product.brand = resultSet.getString(BRAND)
        product.description = resultSet.getString(DESCRIPTION)
        product.color = resultSet.getString(COLOR)
        product.price = resultSet.getBigDecimal(PRICE)
        product
    }

}
