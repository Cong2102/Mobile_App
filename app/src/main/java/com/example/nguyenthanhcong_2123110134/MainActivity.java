package com.example.nguyenthanhcong_2123110134;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenthanhcong_2123110134.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerCategory, recyclerProduct;
    CategoryAdapter categoryAdapter;
    ProductAdapter productAdapter;
    List<Category> categoryList;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);
        if (!isLoggedIn) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
            return;
        }

        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        recyclerCategory = findViewById(R.id.recycler_category);
        recyclerProduct = findViewById(R.id.recycler_product);

        // Danh sách thương hiệu
        categoryList = new ArrayList<>();
        categoryList.add(new Category("Adidas", R.drawable.adidas));
        categoryList.add(new Category("Nike", R.drawable.nike));
        categoryList.add(new Category("Puma", R.drawable.puma));
        categoryList.add(new Category("Zara", R.drawable.zara));
        categoryList.add(new Category("Gucci", R.drawable.gucci));

        categoryAdapter = new CategoryAdapter(this, categoryList, categoryName -> {
            List<Product> filtered = new ArrayList<>();
            for (Product p : productList) {
                if (p.getCategory().equalsIgnoreCase(categoryName)) {
                    filtered.add(p);
                }
            }

            productAdapter = new ProductAdapter(this, filtered);
            recyclerProduct.setAdapter(productAdapter);
        });
        recyclerCategory.setAdapter(categoryAdapter);
        recyclerCategory.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        // Danh sách sản phẩm
        productList = new ArrayList<>();

        productList.add(new Product(R.drawable.jacket1, "Casual Denim Set", 250000, "⭐ 4.6  ", "Adidas", "Bộ denim thoải mái phù hợp đi chơi, dạo phố"));

        productList.add(new Product(R.drawable.blazer, "Elegant Black Blazer", 320000,"⭐ 4.2", "Adidas",  "Blazer đen thanh lịch, phù hợp đi làm và sự kiện"));
        productList.add(new Product(R.drawable.blazer, "Business Blazer", 30000,"⭐ 4.3"   , "Zara","Blazer công sở phong cách trẻ trung"));
        productList.add(new Product(R.drawable.jacket1, "Cool Jacket", 4000000,"⭐ 4.8"   ,"Puma","Áo khoác cá tính cho mùa đông, chất liệu cao cấp"));

        productList.add(new Product(R.drawable.jacket3, "Elegant Black Blazer", 320000,"⭐ 4.2", "Adidas",  "Blazer đen thanh lịch, phù hợp đi làm và sự kiện"));
        productList.add(new Product(R.drawable.businessblazer, "Business Blazer", 30000,"⭐ 4.3"   , "Zara","Blazer công sở phong cách trẻ trung"));
        productList.add(new Product(R.drawable.jacket2, "Cool Jacket", 4000000,"⭐ 4.8"   ,"Puma","Áo khoác cá tính cho mùa đông, chất liệu cao cấp"));

// thêm các sản phẩm khác...


        productAdapter = new ProductAdapter(this, productList);
        recyclerProduct.setAdapter(productAdapter);
        recyclerProduct.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));





        BottomNavigationView nav = findViewById(R.id.bottom_navigation);

        nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.menu_explore) {
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
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }

            return false;
        });

    }

}
