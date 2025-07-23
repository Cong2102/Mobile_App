package com.example.nguyenthanhcong_2123110134;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenthanhcong_2123110134.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView imgProduct;
    TextView txtName, txtPrice;
    Button btnAddToCart, btnBuyNow;
    RecyclerView recyclerRelated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        imgProduct = findViewById(R.id.img_product);
        txtName = findViewById(R.id.txt_name);
        txtPrice = findViewById(R.id.txt_price);
        btnAddToCart = findViewById(R.id.btn_add_to_cart);
        btnBuyNow = findViewById(R.id.btn_buy_now);
        recyclerRelated = findViewById(R.id.recycler_related);

        // Nhận dữ liệu từ Intent
        int image = getIntent().getIntExtra("image", R.drawable.jacket1);
        String name = getIntent().getStringExtra("name");
        String priceStr = getIntent().getStringExtra("price");
        String rating = getIntent().getStringExtra("rating");
        String category = getIntent().getStringExtra("category");
        String description = getIntent().getStringExtra("description");

        // Parse giá từ chuỗi
        int price = 0;
        try {
            price = Integer.parseInt(priceStr.replaceAll("[^0-9]", ""));
        } catch (Exception e) {
            price = 0;
        }

        // Tạo đối tượng sản phẩm
        final Product product = new Product(
                image,
                name != null ? name : "Không rõ",
                price,
                rating != null ? rating : "⭐ 4.5",
                category != null ? category : "Không rõ",
                description != null ? description : "Mô tả đang cập nhật"
        );

        // Hiển thị thông tin sản phẩm
        imgProduct.setImageResource(image);
        txtName.setText(name);
        txtPrice.setText(priceStr);

        // Thêm vào giỏ hàng
        // Thêm vào giỏ hàng
        btnAddToCart.setOnClickListener(v -> {
            // Truyền giá dưới dạng int
            CartItem item = new CartItem(product.getImageResId(), product.getName(), product.getPrice());
            CartManager.addToCart(this, item);
            Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        });

// Mua ngay
        btnBuyNow.setOnClickListener(v -> {
            CartItem item = new CartItem(product.getImageResId(), product.getName(), product.getPrice());
            CartManager.addToCart(this, item);
            Toast.makeText(this, "Thêm vào giỏ & chuyển tới thanh toán", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, CartActivity.class));
        });


        // Danh sách sản phẩm liên quan
        List<Product> relatedProducts = new ArrayList<>();
        relatedProducts.add(new Product(R.drawable.jacket1, "Casual Denim Set", 250000, "⭐ 4.6", "Adidas", "qwasdas"));
        relatedProducts.add(new Product(R.drawable.blazer, "Elegant Black Blazer", 320000, "⭐ 4.2", "Adidas", "qwasdas"));
        relatedProducts.add(new Product(R.drawable.blazer, "Business Blazer", 30000, "⭐ 4.3", "Zara", "qwasdas"));
        relatedProducts.add(new Product(R.drawable.jacket1, "Cool Jacket", 4000000, "⭐ 4.8", "Puma", "qwasdas"));

        ProductAdapter adapter = new ProductAdapter(this, relatedProducts);
        recyclerRelated.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerRelated.setAdapter(adapter);

        // Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.menu_explore);

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
                startActivity(new Intent(getApplicationContext(), MyOrderActivity.class));
                return true;
            } else if (id == R.id.menu_profile) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                return true;
            }
            return false;
        });
    }
}
