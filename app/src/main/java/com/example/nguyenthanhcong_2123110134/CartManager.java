package com.example.nguyenthanhcong_2123110134;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CartManager {
    private static final String CART_KEY = "cart_items";

    // Lưu giỏ hàng
    public static void setCart(Context context, List<CartItem> items) {
        SharedPreferences prefs = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(items);
        editor.putString(CART_KEY, json);
        editor.apply();
    }

    // Lấy giỏ hàng
    public static List<CartItem> getCart(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        String json = prefs.getString(CART_KEY, null);
        if (json != null) {
            Type type = new TypeToken<List<CartItem>>() {}.getType();
            return new Gson().fromJson(json, type);
        }
        return new ArrayList<>();
    }
    public static void addToCart(Context context, CartItem newItem) {
        List<CartItem> cart = getCart(context);

        // Nếu sản phẩm đã có, tăng số lượng
        for (CartItem item : cart) {
            if (item.getName().equals(newItem.getName())) {
                item.increaseQuantity();
                setCart(context, cart);
                return;
            }
        }

        // Nếu chưa có, thêm mới
        cart.add(newItem);
        setCart(context, cart);
    }

}
