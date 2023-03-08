package com.example.myproductsapp.productviews;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myproductsapp.R;
import com.example.myproductsapp.localdb.LocalSource;
import com.example.myproductsapp.model.Product;
import com.example.myproductsapp.model.Repository;
import com.example.myproductsapp.network.RetrofitClient;
import com.example.myproductsapp.presenter.Presenter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ProductFragment extends Fragment implements ProductViewInterface, OnProductClickListener {
    Presenter presenter;
    Button addToFavs;
    private int productId;
    private Product product;
    private TextView titleText;
    private TextView brandText;
    private TextView priceText;
    private TextView descText;
    private RatingBar ratingBar;
    private ImageView thumbImg;
    private RecyclerView rcView;

    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            productId = ((ProductActivity) requireActivity()).getProductID();
        } else {
            productId = savedInstanceState.getInt("id");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getUiRefs(view);
        presenter = new Presenter(this, Repository.getInstance(RetrofitClient.getInstance(), LocalSource.getInstance(this.getContext())));
        presenter.getProduct(productId);
        addToFavs.setOnClickListener(v -> onClick(product));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("id", productId);
    }

    @Override
    public void showProducts(List<Product> productList) {
        Log.i("TAG", "showProducts: one product shower");
        product = productList.get(0);
        updateUI();
    }

    @Override
    public void addProduct(Product product) {
        presenter.addToFavorites(product);
    }

    @Override
    public void showError(String msg) {
        Snackbar.make(rcView, msg, Snackbar.LENGTH_SHORT).show();
    }


    private void updateUI() {
        titleText.setText(product.getTitle());
        brandText.setText(product.getBrand());
        priceText.setText(product.getTextPrice());
        descText.setText(product.getDescription());
        ratingBar.setRating(product.getRating());
        Glide.with(this).load(product.getThumbnail()).into(thumbImg);
    }

    private void getUiRefs(View view) {
        addToFavs = view.findViewById(R.id.addButton);
        titleText = view.findViewById(R.id.textTitle);
        brandText = view.findViewById(R.id.brandText);
        priceText = view.findViewById(R.id.priceText);
        descText = view.findViewById(R.id.textDesc);
        ratingBar = view.findViewById(R.id.frg_ratingBar);
        thumbImg = view.findViewById(R.id.img_Thumbnail);
        rcView = view.findViewById(R.id.rc_view);
    }

    @Override
    public void onClick(Product product) {
        addProduct(product);
        Toast.makeText(this.requireContext(), product.getTitle() + "\nadded to favs", Toast.LENGTH_SHORT).show();
    }
}