package com.stream.dictionary;

public enum OrderStatus {
    ORDERED, IN_PROGRESS, DELIVERED;

    public static OrderStatus returnOrderStatusById(int id) {
        return switch (id) {
            case 1 -> ORDERED;
            case 2 -> IN_PROGRESS;
            case 3 -> DELIVERED;
            default -> throw new IllegalArgumentException("Not real id!");
        };
    }
}

