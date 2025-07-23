package com.example.nguyenthanhcong_2123110134;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenthanhcong_2123110134.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.example.nguyenthanhcong_2123110134.CartManager;
public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CartAdapter adapter;
    List<CartItem> cartItems;
    TextView txtTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Ánh xạ RecyclerView và TextView
        recyclerView = findViewById(R.id.recycler_cart);
        txtTotalPrice = findViewById(R.id.txt_total_price);
        Button btnCheckout = findViewById(R.id.btn_checkout);

        // Lấy danh sách sản phẩm từ CartManager
        cartItems = CartManager.getCart(this);

        // Tạo Adapter và gán vào RecyclerView
        adapter = new CartAdapter(this, cartItems, txtTotalPrice);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Sự kiện khi nhấn nút thanh toán
        btnCheckout.setOnClickListener(v -> {
            int total = 0;
            for (CartItem item : cartItems) {
                total += item.getTotalPrice();  // Tính tổng giá
            }

            String orderId = "ORD" + System.currentTimeMillis();
            String date = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(new java.util.Date());
            Order order = new Order(orderId, new ArrayList<>(cartItems), date, total);

            // Tạo danh sách các OrderItem từ cartItems
            List<OrderItem> orderItems = new ArrayList<>();
            for (CartItem cartItem : cartItems) {
                OrderItem orderItem = new OrderItem(cartItem.getProductName(), cartItem.getPrice(), cartItem.getQuantity());
                orderItems.add(orderItem);
            }

// Truyền orderItems vào OrderActivity
            Intent intent = new Intent(CartActivity.this, OrderActivity.class);
            intent.putExtra("orderItems", (Serializable) orderItems); // 👈 sửa lại dòng này
            startActivity(intent);



            // Xóa giỏ hàng sau khi thanh toán
            cartItems.clear();
            CartManager.setCart(this, cartItems);
            adapter.notifyDataSetChanged();

            // Cập nhật lại tổng tiền
            updateTotalPrice();

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

    // Cập nhật tổng tiền
    private void updateTotalPrice() {
        int total = 0;
        for (CartItem item : cartItems) {
            total += item.getTotalPrice();  // Tính tổng tiền của tất cả các sản phẩm trong giỏ
        }
        txtTotalPrice.setText("Tổng tiền: " + total + " VNĐ");  // Cập nhật tổng tiền vào TextView
    }

}
