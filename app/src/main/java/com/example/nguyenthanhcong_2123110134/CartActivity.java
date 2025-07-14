package com.example.nguyenthanhcong_2123110134;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenthanhcong_2123110134.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CartAdapter adapter;
    List<CartItem> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Ánh xạ RecyclerView
        recyclerView = findViewById(R.id.recycler_cart);
        TextView txtTotalPrice = findViewById(R.id.txt_total_price);
        Button btnCheckout = findViewById(R.id.btn_checkout);
        // Lấy danh sách sản phẩm từ CartManager
        cartItems = CartManager.getCart(this);

        // Tạo Adapter và gán vào RecyclerView
        adapter = new CartAdapter(this, cartItems, txtTotalPrice);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        btnCheckout.setOnClickListener(v -> {
            int total = 0;
            for (CartItem item : cartItems) {
                total += item.getTotalPrice();
            }

            String orderId = "ORD" + System.currentTimeMillis();
            String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.getDefault()).format(new java.util.Date());

            Order order = new Order(orderId, new ArrayList<>(cartItems), date, total);
            OrderManager.saveOrder(this, order);

            // Xoá giỏ hàng sau khi đặt hàng
            cartItems.clear();
            CartManager.setCart(this, cartItems);
            adapter.notifyDataSetChanged();

            Toast.makeText(this, "Đơn hàng đã được lưu!", Toast.LENGTH_SHORT).show();

            // Chuyển sang màn hình Đơn hàng
            startActivity(new Intent(this, MyOrderActivity.class));
        });

        // Bottom Navigation
        BottomNavigationView nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(R.id.menu_cart);

        nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_explore) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (id == R.id.menu_cart) {
                return true;
            } else if (id == R.id.menu_wishlist) {
                startActivity(new Intent(this, WishlistActivity.class));
                return true;
            } else if (id == R.id.menu_order) {
                startActivity(new Intent(this, MyOrderActivity.class));
                return true;
            } else if (id == R.id.menu_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }
            return false;
        });
    }
}
