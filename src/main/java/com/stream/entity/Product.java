package com.stream.entity;

import com.stream.dictionary.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Product {
    private final Long id;
    private final String name;
    private final ProductCategory category;
    private final BigDecimal price;

    public static int compareToPrice(Product p1, Product p2){
        if(p1.getPrice().compareTo(p2.getPrice()) > 0 )
            return 1;
        return -1;
    }

    public static int compareToId(Product p1, Product p2){
        if(p1.getId().compareTo(p2.getId()) > 0 )
            return 1;
        return -1;
    }
}
