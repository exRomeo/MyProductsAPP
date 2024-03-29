package com.example.myproductsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class ProductListFragment extends Fragment implements NetworkDelegate, OnProductClickListener {
    private RecyclerView recyclerView;
    private ArrayList<Product> products;
    private ProductsModel productsModel;
    private ProductAdapter productAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView holder;

    public ProductListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rc_view);
        productsModel = ProductsModel.getInstance(this.getContext());
        layoutManager = new LinearLayoutManager(this.getContext());
        productAdapter = new ProductAdapter(this.getContext(), new ArrayList<>(), this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(productAdapter);
        RetrofitClient retroFitClient = new RetrofitClient(this);
        retroFitClient.startCall();
    }


    @Override
    public void onResponseSuccess(List<Product> productList) {
        productAdapter.setProducts(productList);
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResponseFailure(String msg) {
        Snackbar.make(recyclerView, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(Product product) {
        productsModel.addProduct(product);
        Toast.makeText(this.getContext(), product.getTitle() + "\nadded to favs", Toast.LENGTH_SHORT).show();
    }
}