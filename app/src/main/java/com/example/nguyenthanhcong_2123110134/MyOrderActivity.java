package com.example.nguyenthanhcong_2123110134;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nguyenthanhcong_2123110134.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MyOrderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        setTitle("Đơn hàng của tôi");

        BottomNavigationView nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(R.id.menu_order);
        // đánh dấu đang ở tab đơn hàng

        nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_explore) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (id == R.id.menu_cart) {
                startActivity(new Intent(this, CartActivity.class));
                return true;
            } else if (id == R.id.menu_wishlist) {
                startActivity(new Intent(this, WishlistActivity.class));
                return true;
            } else if (id == R.id.menu_order) {
                return true; // đang ở trang này
            } else if (id == R.id.menu_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }
            return false;
        });
    }
}
