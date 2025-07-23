package com.example.nguyenthanhcong_2123110134;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private TextView txtOrderTotal;
    private List<OrderItem> orderItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Ánh xạ RecyclerView và TextView
        recyclerView = findViewById(R.id.recycler_order);
        txtOrderTotal = findViewById(R.id.txt_order_total);

        // Nhận thông tin đơn hàng từ CartActivity (orderItems)
        orderItems = (List<OrderItem>) getIntent().getSerializableExtra("orderItems");

        // Kiểm tra nếu orderItems là null, khởi tạo danh sách mới
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        if (orderItems == null || orderItems.isEmpty()) {
            Toast.makeText(this, "Không có sản phẩm để hiển thị!", Toast.LENGTH_SHORT).show();
        }

        // Hiển thị thông tin tổng tiền đơn hàng
        int total = 0;
        for (OrderItem item : orderItems) {
            total += item.getPrice() * item.getQuantity(); // Tính tổng tiền
        }
        txtOrderTotal.setText("Tổng tiền: " + total + " VNĐ");

        // Khởi tạo RecyclerView và Adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Gán adapter cho RecyclerView
        orderAdapter = new OrderAdapter(orderItems);
        recyclerView.setAdapter(orderAdapter);

        // Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.menu_order);

        bottomNavigationView.setOnItemSelectedListener(itemMenu -> {
            int id = itemMenu.getItemId();
            if (id == R.id.menu_explore) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                return true;
            } else if (id == R.id.menu_cart) {
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
                return true;
            } else if (id == R.id.menu_wishlist) {
                startActivity(new Intent(getApplicationContext(), WishlistActivity.class));
                return true;
            } else if (id == R.id.menu_order) {
                return true;
            } else if (id == R.id.menu_profile) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                return true;
            }
            return false;
        });
    }
}
