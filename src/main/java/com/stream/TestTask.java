package com.stream;

import com.stream.dictionary.ProductCategory;
import com.stream.entity.Customer;
import com.stream.entity.Order;
import com.stream.entity.Product;

import java.math.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestTask
{
    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    private static final BigDecimal PERCENTAGE_DISCOUNT = new BigDecimal(100);

    public void testFirst(GeneratedData data){
        List<Product> products = data.getCustomersFullList().stream()
                .flatMap(customers -> customers.getOrders().stream().flatMap(orders -> orders.getProducts().stream()))
                .filter(product -> product.getCategory().equals(ProductCategory.BOOK) && product.getPrice().compareTo(BigDecimal.valueOf(100)) > 0)
                .sorted(Product::compareToId)
                .toList();

        products.forEach(System.out::println);
    }

    public void testSecond(GeneratedData data){
        List<Order> products = data.getCustomersFullList().stream()
                .flatMap(customers -> customers.getOrders().stream())
                .filter(orders -> orders.getProducts().stream()
                        .anyMatch(product -> product.getCategory().equals(ProductCategory.CHILDREN_PRODUCT)))
                .sorted(Order::compareToId)
                .toList();

        products.forEach(System.out::println);
    }

    public void testThird(GeneratedData data){
        var products = data.getCustomersFullList().stream()
                .flatMap(customers -> customers.getOrders().stream().flatMap(orders -> orders.getProducts().stream()))
                .filter(product -> product.getCategory().equals(ProductCategory.TOY))
                .map(product -> toPercentageOf(PERCENTAGE_DISCOUNT, product.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println(products);
    }
    public void testFourth(GeneratedData data){
        var products = data.getCustomersFullList().stream()
                .filter(customer -> customer.getLevel() == 2)
                .flatMap(customer -> customer.getOrders().stream())
                .filter(orders-> !orders.getOrderDate().isBefore(LocalDate.of(2023, 4, 25))
                        && !orders.getOrderDate().isAfter(LocalDate.of(2023, 5, 25)))
                .toList();

        products.forEach(System.out::println);
    }

    public void testFifth(GeneratedData data) {
        List<Product> topMinBookByPrice = data.getCustomersFullList().stream()
                .flatMap(customers -> customers.getOrders().stream().flatMap(orders -> orders.getProducts().stream()))
                .filter(product -> product.getCategory().equals(ProductCategory.BOOK))
                .sorted(Product::compareToId)
                .limit(2)
                .toList();

        topMinBookByPrice.forEach(System.out::println);
    }

    public void testSixth(GeneratedData data) {
        var orders = data.getCustomersFullList().stream()
                .flatMap(customers -> customers.getOrders().stream())
                .sorted(Order::compareOrderDate)
                .limit(3)
                .toList();

        orders.forEach(order -> System.out.println(order.getId() + " " + order.getOrderDate()));
    }
    public void testSeventh(GeneratedData data) {
         data.getCustomersFullList().stream()
                .flatMap(customers -> customers.getOrders().stream())
                .filter(orders -> orders.getOrderDate().isAfter(LocalDate.of(2024, 1, 26)) && orders.getOrderDate().isBefore(LocalDate.of(2024, 4, 26)))
                .peek(orders -> System.out.println("Order id: " + orders.getId() + "\nOrder date: " + orders.getOrderDate()))
                .flatMap(orders -> orders.getProducts().stream())
                .forEach(product -> System.out.println("\tProduct id: " + product.getId()));
    }

    public void testEighth(GeneratedData data) {
        data.getCustomersFullList().stream()
                .flatMap(customers -> customers.getOrders().stream())
                .filter(orders -> orders.getOrderDate().equals(LocalDate.of(2024, 6, 26)))
                .flatMap(orders -> orders.getProducts().stream())
                .map(Product::getPrice)
                .reduce(BigDecimal::add)
                .ifPresent(System.out::println);
    }
    public void testNinth(GeneratedData data) {
        var averagePaymentsByMonth = data.getCustomersFullList().stream()
                .flatMap(customers -> customers.getOrders().stream())
                .filter(orders -> orders.getOrderDate().getMonth() == LocalDate.of(2024, 6, 30).getMonth()
                        && orders.getOrderDate().getYear() == LocalDate.of(2024, 6, 30).getYear())
                .peek(orders -> System.out.println(orders.getId() + " " + orders.getOrderDate()))
                .flatMap(orders -> orders.getProducts().stream().map(Product::getPrice)).mapToInt(BigDecimal::intValue).average();
        averagePaymentsByMonth.ifPresent(System.out::println);
    }

    public void testTenth(GeneratedData data) {
        Map<String, Double> statisticsMap = data.getCustomersFullList().stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .filter(product -> product.getCategory().equals(ProductCategory.BOOK))
                .collect(Collectors.collectingAndThen(
                        Collectors.summarizingDouble(product -> product.getPrice().doubleValue()),
                        stats -> Map.of(
                                "Сумма", stats.getSum(),
                                "Среднее", stats.getAverage(),
                                "Максимум", stats.getMax(),
                                "Минимум", stats.getMin(),
                                "Количество", (double) stats.getCount()
                        )
                ));

        statisticsMap.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    public void testEleventh(GeneratedData data) {
        Map<Long, Integer> newData = data.getCustomersFullList().stream()
                .flatMap(customers -> customers.getOrders().stream())
                .collect(Collectors.toMap(
                        Order::getId,
                        orders -> orders.getProducts().size()
                ));

        newData.forEach((key, value) -> System.out.println("Order id: " + key + " Product count: " + value));
    }

    public void testTwelfth(GeneratedData data) {
        Map<Customer, List<Order>> newData = data.getCustomersFullList().stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        customer -> customer.getOrders().stream().toList()
                ));

        newData.entrySet().stream()
                .sorted(Comparator.comparing(entry -> entry.getKey().getId()))
                .forEach(entry-> System.out.println("Customer id: " + entry.getKey().getId() + " Order count: " + entry.getValue().size()));
    }

    public void testThirteenth(GeneratedData data) {
        Map<Order, Double> newData = data.getCustomersFullList().stream()
                .flatMap(customer -> customer.getOrders().stream())
                .collect(Collectors.toMap(
                        Function.identity(),
                        order -> order.getProducts().stream()
                                .map(Product::getPrice)
                                .map(BigDecimal::doubleValue)
                                .reduce(0.0, Double::sum)
                ));

        newData.entrySet().stream()
                .sorted(Comparator.comparing(entry -> entry.getKey().getId()))
                .forEach(entry-> System.out.println("Customer id: " + entry.getKey().getId() + " Order count: " + entry.getValue()));
    }

    public void testFourteenth(GeneratedData data) {
        Map<String, List<String>> newData = data.getCustomersFullList().stream()
                .flatMap(customer -> customer.getOrders().stream())
                .collect(Collectors.toMap(
                        order -> order.getProducts().stream().map(Product::getCategory).findFirst().toString(),
                        order -> order.getProducts().stream().map(Product::getName).collect(Collectors.toList()),
                        (list1, list2) -> {
                            list1.addAll(list2);
                            return list1;}
                ));
        newData.forEach((key, value) -> System.out.println("Product category: " + key + " Product name: " + value));
    }
    public void testFifteenth(GeneratedData data) {
        Map<String, Product> newData = data.getCustomersFullList().stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.toMap(
                        product -> product.getCategory().toString(),
                        Function.identity(),
                        (existing, replacement) -> existing.getPrice().compareTo(replacement.getPrice()) >= 0 ? existing : replacement // Сравнение по цене для нахождения максимального
                ));

        newData.forEach((key, value) -> System.out.println("Product category: " + key + " Product price: " + value.getPrice()));
    }

    public BigDecimal toPercentageOf(BigDecimal percentage, BigDecimal total){
        return percentage.divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP).add(BigDecimal.valueOf(1)).multiply(total);
    }
}
