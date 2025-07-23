package com.example.nguyenthanhcong_2123110134;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.nguyenthanhcong_2123110134.CartItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
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

    // Thêm sản phẩm vào giỏ hàng
    public static void addToCart(Context context, CartItem newItem) {
        List<CartItem> cart = getCart(context);

        // Kiểm tra sản phẩm đã có trong giỏ hàng hay chưa
        boolean productExists = false;

        for (CartItem item : cart) {
            // Kiểm tra nếu sản phẩm đã có trong giỏ hàng (so sánh theo tên và giá)
            if (item.getProductName().equals(newItem.getProductName()) && item.getPrice() == newItem.getPrice()) {
                item.increaseQuantity();  // Tăng số lượng nếu sản phẩm đã có
                productExists = true;
                break;  // Dừng vòng lặp khi tìm thấy sản phẩm
            }
        }

        // Nếu sản phẩm chưa có trong giỏ, thêm mới sản phẩm
        if (!productExists) {
            cart.add(newItem);
        }

        // Lưu giỏ hàng vào SharedPreferences
        setCart(context, cart);
    }

    // Xóa sản phẩm khỏi giỏ hàng

    public static void removeFromCart(Context context, CartItem itemToRemove) {
        List<CartItem> cart = getCart(context);  // Lấy giỏ hàng hiện tại

        for (CartItem item : cart) {
            // Nếu sản phẩm trong giỏ trùng với sản phẩm cần xóa
            if (item.getProductName().equals(itemToRemove.getProductName())) {
                cart.remove(item);  // Xóa sản phẩm khỏi giỏ
                break;  // Dừng vòng lặp sau khi xóa sản phẩm
            }
        }

        // Lưu giỏ hàng đã thay đổi vào SharedPreferences
        setCart(context, cart);
    }


    // Cập nhật số lượng sản phẩm trong giỏ
    public static void updateQuantity(Context context, CartItem item, int newQuantity) {
        List<CartItem> cart = getCart(context);

        for (CartItem cartItem : cart) {
            if (cartItem.getProductName().equals(item.getProductName())) {
                cartItem.setQuantity(newQuantity); // Cập nhật số lượng
                setCart(context, cart); // Lưu lại giỏ hàng sau khi thay đổi
                return;
            }
        }
    }
}
