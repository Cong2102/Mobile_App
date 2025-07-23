package com.example.nguyenthanhcong_2123110134;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private String orderId;
    private List<CartItem> products;
    private String date;
    private int total;

    // Constructor, getters, setters, etc.
    public Order(String orderId, List<CartItem> products, String date, int total) {
        this.orderId = orderId;
        this.products = products;
        this.date = date;
        this.total = total;
    }

    // Getters and Setters

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<CartItem> getProducts() {
        return products;
    }

    public void setProducts(List<CartItem> products) {
        this.products = products;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
