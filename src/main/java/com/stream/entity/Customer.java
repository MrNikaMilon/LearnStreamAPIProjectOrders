package com.stream.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class Customer {
    private final Long id;
    private final String name;
    private final Long level;
    private final Set<Order> orders;

    public static int compareToId(Customer c1, Customer c2){
        if(c1.getId().compareTo(c2.getId()) > 0 )
            return 1;
        return -1;
    }

    public void putOrderInSet(Order order){
        orders.add(order);
    }
}
