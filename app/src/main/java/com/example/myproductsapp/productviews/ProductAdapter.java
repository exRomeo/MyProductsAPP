package com.example.myproductsapp.productviews;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myproductsapp.R;
import com.example.myproductsapp.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private final Context context;
    private List<Product> products;
    private final OnProductClickListener onProductClickListener;
    Drawable icon;

    public ProductAdapter(Context context, List<Product> products, OnProductClickListener onProductClickListener, Drawable icon) {
        this.context = context;
        this.products = products;
        this.onProductClickListener = onProductClickListener;
        this.icon = icon;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Product currentProduct = products.get(position);
        holder.getName().setText(currentProduct.getTitle());
        holder.getPrice().setText(currentProduct.getTextPrice());
        holder.getRatingBar().setRating(currentProduct.getRating());
        Glide.with(context).load(currentProduct.getThumbnail()).into(holder.getImg());
        holder.getCardView().setOnClickListener(view1 -> {
            Intent toProduct = new Intent(context, ProductActivity.class);
            toProduct.putExtra("dest",3);
            toProduct.putExtra("product", currentProduct.getId());
            context.startActivity(toProduct);
        });
        holder.getCardView().findViewById(R.id.favButton).setBackground(icon);
        holder.getCardView().findViewById(R.id.favButton).setOnClickListener(v -> onProductClickListener.onClick(currentProduct));


    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        private final TextView name;
        private final TextView price;
        private final ImageView img;
        private final RatingBar ratingBar;

        public ViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.tv_productName);
            price = view.findViewById(R.id.tv_price);
            img = view.findViewById(R.id.iv_img);
            ratingBar = view.findViewById(R.id.frg_ratingBar);
            cardView = view.findViewById(R.id.card);
        }

        public CardView getCardView() {
            return cardView;
        }

        public TextView getName() {
            return name;
        }

        public TextView getPrice() {
            return price;
        }

        public ImageView getImg() {
            return img;
        }

        public RatingBar getRatingBar() {
            return ratingBar;
        }
    }
}
