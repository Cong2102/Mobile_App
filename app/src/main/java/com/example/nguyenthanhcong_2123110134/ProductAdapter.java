package com.example.nguyenthanhcong_2123110134;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenthanhcong_2123110134.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.productList = products;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product p = productList.get(position);
        holder.imgProduct.setImageResource(p.getImageResId());
        holder.txtName.setText(p.getName());
        holder.txtPrice.setText(String.valueOf(p.getPrice())); // ✅ Đúng


        // Đặt sự kiện click ở đây
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("image", p.getImageResId());
            intent.putExtra("name", p.getName());
            intent.putExtra("price", String.valueOf(p.getPrice()));
            intent.putExtra("rating", p.getRating());
            intent.putExtra("category", p.getCategory());
            intent.putExtra("description", p.getDescription());
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView txtName, txtPrice;

        public ProductViewHolder(View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product);
            txtName = itemView.findViewById(R.id.txt_name);
            txtPrice = itemView.findViewById(R.id.txt_price);
        }
    }
}
