package com.example.nguyenthanhcong_2123110134;

public class CartItem {
    private int image;
    private String name;
    private String price; // dạng "100000"
    private int quantity;

    public CartItem(int image, String name, String price) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.quantity = 1;
    }

    public int getImage() { return image; }
    public String getName() { return name; }
    public String getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void increaseQuantity() { quantity++; }
    public void decreaseQuantity() {
        if (quantity > 0) quantity--;
    }

    public int getTotalPrice() {
        try {
            return Integer.parseInt(price.replaceAll("[^0-9]", "")) * quantity;
        } catch (Exception e) {
            return 0;
        }
    }
}



