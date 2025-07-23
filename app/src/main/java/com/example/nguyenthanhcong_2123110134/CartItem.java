package com.example.nguyenthanhcong_2123110134;

public class CartItem {
    private int imageResId;  // ID hình ảnh sản phẩm
    private String productName;     // Tên sản phẩm
    private int price;       // Giá sản phẩm
    private int quantity;    // Số lượng sản phẩm

    // Constructor
    public CartItem(int imageResId, String productName, int price) {
        this.imageResId = imageResId;
        this.productName = productName;
        this.price = price;
        this.quantity = 1;  // Mặc định số lượng là 1
    }

    // Getter và Setter cho các thuộc tính
    public int getImageResId() {
        return imageResId;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public int getTotalPrice() {
        return price * quantity;  // Tính tổng giá sản phẩm
    }
}


