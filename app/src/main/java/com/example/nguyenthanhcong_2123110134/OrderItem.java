package com.example.nguyenthanhcong_2123110134;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private String productName;
    private int price;
    private int quantity;

    // Constructor
    public OrderItem(String productName, int price, int quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters
    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
