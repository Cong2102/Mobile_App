package com.example.nguyenthanhcong_2123110134;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;
    private Context context;
    private TextView txtTotalPrice; // Tham chiếu tới TextView tổng tiền

    public CartAdapter(Context context, List<CartItem> cartItems, TextView txtTotalPrice) {
        this.context = context;
        this.cartItems = cartItems;
        this.txtTotalPrice = txtTotalPrice;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.productName.setText(item.getProductName());
        holder.productPrice.setText(item.getPrice() + " VNĐ");
        holder.productQuantity.setText(String.valueOf(item.getQuantity()));

        // Tăng số lượng
        holder.btnIncrease.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            notifyItemChanged(position);  // Cập nhật item hiện tại
            updateTotalPrice(); // Cập nhật lại tổng tiền
        });

        // Giảm số lượng
        holder.btnDecrease.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                notifyItemChanged(position);  // Cập nhật item hiện tại
                updateTotalPrice(); // Cập nhật lại tổng tiền
            }
        });

        // Xóa sản phẩm
        holder.btnRemove.setOnClickListener(v -> {
            // Xóa sản phẩm khỏi giỏ hàng
            CartManager.removeFromCart(context, item);  // Xóa sản phẩm từ SharedPreferences

            // Xóa sản phẩm khỏi danh sách của RecyclerView
            // Xóa sản phẩm trong giỏ hàng
            cartItems.remove(position); // Xóa sản phẩm khỏi danh sách giỏ hàng
            notifyItemRemoved(position); // Cập nhật lại RecyclerView
            // Cập nhật lại RecyclerView

            // Cập nhật lại giỏ hàng trong SharedPreferences
            CartManager.setCart(context, cartItems);

            // Cập nhật tổng tiền
            updateTotalPrice();
        });

    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    // Hàm cập nhật tổng tiền
    private void updateTotalPrice() {
        int total = 0;
        for (CartItem item : cartItems) {
            total += item.getTotalPrice();  // Tính tổng tiền của tất cả các sản phẩm trong giỏ
        }
        // Cập nhật tổng tiền vào TextView trong CartActivity
        txtTotalPrice.setText("Tổng tiền: " + total + " VNĐ");
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice, productQuantity;
        Button btnIncrease, btnDecrease, btnRemove;

        public CartViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            productQuantity = itemView.findViewById(R.id.product_quantity);
            btnIncrease = itemView.findViewById(R.id.btn_increase);
            btnDecrease = itemView.findViewById(R.id.btn_decrease);
            btnRemove = itemView.findViewById(R.id.btn_remove);
        }
    }
}
