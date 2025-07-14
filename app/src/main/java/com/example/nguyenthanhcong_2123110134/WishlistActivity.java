package com.example.nguyenthanhcong_2123110134;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nguyenthanhcong_2123110134.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WishlistActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        setTitle("Yêu thích");

        BottomNavigationView nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(R.id.menu_wishlist); // Đánh dấu mục đang active

        nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_explore) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (id == R.id.menu_cart) {
                startActivity(new Intent(this, CartActivity.class));
                return true;
            } else if (id == R.id.menu_wishlist) {
                return true; // Đang ở trang này
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
