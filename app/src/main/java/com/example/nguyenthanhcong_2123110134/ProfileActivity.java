package com.example.nguyenthanhcong_2123110134;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nguyenthanhcong_2123110134.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);
        TextView txtName = findViewById(R.id.txt_user_name);
        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        String name = prefs.getString("fullName", "Tên người dùng");
        txtName.setText(name);

        BottomNavigationView nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(R.id.menu_profile);

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
                startActivity(new Intent(this, MyOrderActivity.class));
                return true;
            } else if (id == R.id.menu_profile) {
                return true;
            }
            return false;
        });

        // ✅ Xử lý nút đăng xuất
        Button btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit(); // dùng lại biến đã khai báo phía trên
            editor.putBoolean("isLoggedIn", false);
            editor.apply();

            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });


    }
}