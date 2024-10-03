package com.stream.dictionary;

public enum ProductCategory {
    BOOK, CHILDREN_PRODUCT, TOY;

    public static ProductCategory returnProductCategoryById(int id) {
        return switch (id) {
            case 1 -> BOOK;
            case 2 -> CHILDREN_PRODUCT;
            case 3 -> TOY;
            default -> throw new IllegalArgumentException("Not real id!");
        };
    }
}
