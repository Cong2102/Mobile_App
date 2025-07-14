package com.example.nguyenthanhcong_2123110134;
import android.content.Context;
import android.widget.Button;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenthanhcong_2123110134.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;
    private Context context;
    private TextView txtTotalPrice;

    public CartAdapter(Context context, List<CartItem> cartItems, TextView txtTotalPrice) {
        this.context = context;
        this.cartItems = cartItems;
        this.txtTotalPrice = txtTotalPrice;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.imgProduct.setImageResource(item.getImage());
        holder.txtName.setText(item.getName());
        holder.txtPrice.setText(item.getPrice());
        holder.txtQuantity.setText(String.valueOf(item.getQuantity()));

        holder.btnIncrease.setOnClickListener(v -> {
            item.increaseQuantity();
            notifyItemChanged(position);
            updateTotal();
        });

        holder.btnDecrease.setOnClickListener(v -> {
            item.decreaseQuantity();
            notifyItemChanged(position);
            updateTotal();
        });

        holder.btnRemove.setOnClickListener(v -> {
            cartItems.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, cartItems.size()); // Cập nhật lại danh sách
            CartManager.setCart(context, cartItems); // Cập nhật dữ liệu giỏ hàng
            updateTotal();
        });

    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    private void updateTotal() {
        int total = 0;
        for (CartItem item : cartItems) {
            total += item.getTotalPrice();
        }
        txtTotalPrice.setText("Tổng tiền: " + total + " VNĐ");
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView txtName, txtPrice, txtQuantity;
        Button btnIncrease, btnDecrease, btnRemove;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product);
            txtName = itemView.findViewById(R.id.txt_name);
            txtPrice = itemView.findViewById(R.id.txt_price);
            txtQuantity = itemView.findViewById(R.id.txt_quantity);
            btnIncrease = itemView.findViewById(R.id.btn_increase);
            btnDecrease = itemView.findViewById(R.id.btn_decrease);
            btnRemove = itemView.findViewById(R.id.btn_remove);
        }
    }
}


