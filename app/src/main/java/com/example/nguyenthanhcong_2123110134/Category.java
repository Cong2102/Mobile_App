package com.example.nguyenthanhcong_2123110134;

public class Category {
    private final String name;
    private final int imageResId;

    public Category(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }


    public int getImageResId() {
        return imageResId;
    }
}
