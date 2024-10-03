package com.stream;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class AppTest {
    public GeneratedData mockData = new GeneratedData();

    @Test
    @DisplayName("Test create List's with generated data")
    public void testCreateUsers(){
        System.out.println("Mock user-data has been created");
        assertFalse(mockData.getCustomersData().isEmpty());
    }

    @Test
    @DisplayName("Test create List's with generated data")
    public void testCreateOrders(){
        System.out.println("Mock order-data has been created");
        assertFalse(mockData.getOrdersData().isEmpty());
    }

    @Test
    @DisplayName("Test create List's with generated data")
    public void testCreatedProducts(){
        System.out.println("Mock product-data has been created");
        assertFalse(mockData.getProductsData().isEmpty());
    }

    @Test
    @DisplayName("Check returned full generated data")
    public void testFullGeneratedData(){
        System.out.println("Mock data has been created");
        assertFalse(mockData.mapAllDataToCustomer(
                mockData.getCustomersData(),
                mockData.getOrdersData(),
                mockData.getProductsData())
                .isEmpty());
    }
}
