package com.example.nguyenthanhcong_2123110134;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static final String ORDER_KEY = "orders";

    public static void saveOrder(Context context, Order order) {
        List<Order> currentOrders = getOrders(context);
        currentOrders.add(order);
        SharedPreferences prefs = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String json = new Gson().toJson(currentOrders);
        editor.putString(ORDER_KEY, json);
        editor.apply();
    }

    public static List<Order> getOrders(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        String json = prefs.getString(ORDER_KEY, null);
        if (json != null) {
            Type type = new TypeToken<List<Order>>() {}.getType();
            return new Gson().fromJson(json, type);
        }
        return new ArrayList<>();
    }
}
