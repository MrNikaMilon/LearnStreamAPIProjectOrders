package com.stream;

import org.junit.jupiter.api.Test;

public class TestStartMethod {
    public static GeneratedData mockData = new GeneratedData();
    public static TestTask newTestClass = new TestTask();


    @Test
    public void testFirstTask() {
        System.out.println("//Получите список продуктов из категории \"Books\" с ценой более 100.");
        newTestClass.testFirst(mockData);
    }

    @Test
    public void testSecondTask() {
        System.out.println("//Получите список заказов с продуктами из категории \"Children's products\".");
        newTestClass.testSecond(mockData);
    }

    @Test
    public void testThirdTask() {
        System.out.println("//Получите список продуктов из категории \"Toys\" и примените скидку 10% и получите сумму всех продуктов.");
        newTestClass.testThird(mockData);
    }

    @Test
    public void testFourthTask() {
        System.out.println("//Получите список продуктов, заказанных клиентом второго уровня между 01-фев-2021 и 01-апр-2021.");
        newTestClass.testFourth(mockData);
    }

    @Test
    public void testFifthTask() {
        System.out.println("//Получите топ 2 самые дешевые продукты из категории \"Books\".");
        newTestClass.testFifth(mockData);
    }

    @Test
    public void testSixthTask() {
        System.out.println("//Получите 3 самых последних сделанных заказа.");
        newTestClass.testSixth(mockData);
    }

    @Test
    public void testSeventhTask() {
        System.out.println("//Получите список заказов, сделанных 15-марта-2021, выведите id заказов в консоль и затем верните список их продуктов.");
        newTestClass.testSeventh(mockData);
    }

    @Test
    public void testEightTask() {
        System.out.println("//Рассчитайте общую сумму всех заказов, сделанных в феврале 2021.");
        newTestClass.testEighth(mockData);
    }

    @Test
    public void testNinthTask() {
        System.out.println("//Рассчитайте средний платеж по заказам, сделанным 14-марта-2021.");
        newTestClass.testNinth(mockData);
    }

    @Test
    public void testTenthTask() {
        System.out.println("//Получите набор статистических данных (сумма, среднее, максимум, минимум, количество) для всех продуктов категории \"Книги\".");
        newTestClass.testTenth(mockData);
    }

    @Test
    public void testEleventhTask() {
        System.out.println("//Получите данные Map<Long, Integer> → key - id заказа, value - кол-во товаров в заказе.");
        newTestClass.testEleventh(mockData);
    }

    @Test
    public void testTwelfthTask() {
        System.out.println("//Создайте Map<Customer, List<Order>> → key - покупатель, value - список его заказов.");
        newTestClass.testTwelfth(mockData);
    }

    @Test
    public void testThirteenthTask() {
        System.out.println("//Создайте Map<Order, Double> → key - заказ, value - общая сумма продуктов заказа.");
        newTestClass.testThirteenth(mockData);
    }

    @Test
    public void testFourteenthTask() {
        System.out.println("//Получите Map<String, List<String>> → key - категория, value - список названий товаров в категории.");
        newTestClass.testFourteenth(mockData);
    }

    @Test
    public void testFifteenthTask() {
        System.out.println("//Получите Map<String, Product> → самый дорогой продукт по каждой категории.");
        newTestClass.testFifteenth(mockData);
    }
}
