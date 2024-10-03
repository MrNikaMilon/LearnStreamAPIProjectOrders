package com.stream.entity;


import com.stream.dictionary.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
public class Order {
    private final Long id;
    private final LocalDate orderDate;
    private final LocalDate deliveryDate;
    private final OrderStatus status;
    private final Set<Product> products;

    public static int compareOrderDate(Order o1, Order o2){
        if(o1.getOrderDate().isAfter(o2.getOrderDate()))
            return -1;
        return 1;
    }

    public static int compareToId(Order o1, Order o2){
        if(o1.getId().compareTo(o2.getId()) > 0 )
            return 1;
        return -1;
    }

    public void putProductInSet(Product product){
        products.add(product);
    }

}
