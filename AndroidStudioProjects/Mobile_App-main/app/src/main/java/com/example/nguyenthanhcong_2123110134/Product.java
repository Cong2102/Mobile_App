package com.example.nguyenthanhcong_2123110134;

public class Product {
    private int image;
    private String name;
    private int price;
    private String rating;
    private String category;
    private String description;

    public Product(int image, String name, int price, String rating, String category, String description) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.category = category;
        this.description = description;
    }

    public int getImageResId() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getRating() {
        return rating;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}
