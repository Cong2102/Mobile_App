package com.example.nguyenthanhcong_2123110134;

import java.util.List;

public class Order {
    private String orderId;
    private List<CartItem> items;
    private String date;
    private int totalAmount;

    public Order(String orderId, List<CartItem> items, String date, int totalAmount) {
        this.orderId = orderId;
        this.items = items;
        this.date = date;
        this.totalAmount = totalAmount;
    }

    public String getOrderId() { return orderId; }
    public List<CartItem> getItems() { return items; }
    public String getDate() { return date; }
    public int getTotalAmount() { return totalAmount; }
}
