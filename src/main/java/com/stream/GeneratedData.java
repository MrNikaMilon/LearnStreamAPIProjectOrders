package com.stream;

import com.stream.dictionary.OrderStatus;
import com.stream.dictionary.ProductCategory;
import com.stream.entity.Customer;
import com.stream.entity.Order;
import com.stream.entity.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Data
public class GeneratedData {
    final List<Customer> customersFullList;

    private final Integer COUNT_CUSTOMERS = 5;
    private final Integer COUNT_ORDERS_BY_CUSTOMERS = 5;
    private final Integer COUNT_PRODUCTS_BY_ORDERS = 3;

    GeneratedData(){
        //First iteration
        List<Customer> customers = getCustomersData();

        //Second iteration
        List<Order> orders = getOrdersData();

        //Third iteration
        List<Product> products = getProductsData();

        //Fourth iteration
        customersFullList = mapAllDataToCustomer(customers, orders, products);
    }

    public List<Customer> getCustomersData(){
        return IntStream
                .rangeClosed(0, COUNT_CUSTOMERS-1)
                .mapToObj(index -> new Customer(
                        (long)index +1,
                        "User_name_" + index,
                        (ThreadLocalRandom.current().nextLong(1, 3)),
                        new HashSet<>()))
                .collect(Collectors.toList());
    }

    public List<Order> getOrdersData(){
        return IntStream
                .rangeClosed(0, (COUNT_CUSTOMERS * 5)-1)
                .mapToObj(index -> {
                    OrderStatus orderStatus = OrderStatus.returnOrderStatusById(new Random().nextInt(3) + 1);
                    return new Order(
                            (long)index+1,
                            LocalDate.now().minusYears(2).plusMonths(index),
                            LocalDate.now().minusYears(2).plusMonths(index),
                            orderStatus,
                            new HashSet<>());
                })
                .collect(Collectors.toList());
    }

    public List<Product> getProductsData(){
        return Stream.concat(Stream.concat(
                        IntStream.rangeClosed(1, 20).mapToObj(index -> new Product(
                                (long) index,
                                "Book_"  + index,
                                ProductCategory.BOOK,
                                BigDecimal.valueOf(100 + index))),
                        IntStream.rangeClosed(21, 40).mapToObj(index -> new Product(
                                (long) index,
                                "Book_" + index,
                                ProductCategory.TOY,
                                BigDecimal.valueOf(100 + index)))),
                IntStream.rangeClosed(41, 75).mapToObj(index -> new Product(
                        (long) index,
                        "Book_" + index,
                        ProductCategory.CHILDREN_PRODUCT,
                        BigDecimal.valueOf(100 + index)))).collect(Collectors.toList());
    }

    public List<Customer> mapAllDataToCustomer(List<Customer> customerData, List<Order> orderData, List<Product> productData){
        return customerData.stream()
                .peek(customer -> LongStream
                        .rangeClosed((customer.getId() - 1L) * COUNT_ORDERS_BY_CUSTOMERS + 1L, customer.getId() * COUNT_ORDERS_BY_CUSTOMERS)
                        .mapToObj(orderIndex -> orderData.get((int) Math.min(orderIndex - 1, orderData.size() - 1)))  // Границы для orderData
                        .forEach(order -> {
                            customer.putOrderInSet(order);
                            LongStream
                                    .range((order.getId() - 1L) * COUNT_PRODUCTS_BY_ORDERS, order.getId() * COUNT_PRODUCTS_BY_ORDERS)
                                    .mapToObj(productIndex -> productData.get((int) Math.min(productIndex, productData.size() - 1)))  // Границы для productData
                                    .forEach(order::putProductInSet);
                        })
                ).collect(Collectors.toList());
    }

    public void showGeneratedData(){
        mapAllDataToCustomer(getCustomersData(), getOrdersData(), getProductsData())
                .forEach(customers ->
                        customers.getOrders().stream()
                                .sorted(Comparator.comparing(Order::getId))
                                .peek(orders -> System.out.println("Customer id: " + customers.getId() + " Orders id: " + orders.getId()))
                                .forEach(orders -> orders.getProducts().stream()
                                        .sorted(Comparator.comparing(Product::getId))
                                        .forEach(product -> System.out.println("\t\t\t   Orders id: " + orders.getId() + " Product id: " + product.getId()))));
    }
}
