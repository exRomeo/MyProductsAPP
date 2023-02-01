package com.example.myproductsapp;

import android.os.Bundle;
import android.util.Log;
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

import com.example.myproductsapp.localdb.LocalProducts;
import com.example.myproductsapp.localdb.Product;
import com.example.myproductsapp.network.RetrofitClient;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class ProductListFragment extends Fragment implements ProductView, OnProductClickListener {
    private RecyclerView recyclerView;
    private ArrayList<Product> products;
    private LocalProducts localProducts;
    private ProductAdapter productAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProductPresenter presenter;
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
        Log.i("TAG", "onViewCreated: <<<<<<<<<<<<<<<<");
        recyclerView = view.findViewById(R.id.rc_view);
        localProducts = LocalProducts.getInstance(this.requireContext());
        layoutManager = new LinearLayoutManager(this.requireContext());
        productAdapter = new ProductAdapter(this.requireContext(), new ArrayList<>(), this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(productAdapter);
        RetrofitClient retroFitClient = /*new RetrofitClient(this)*/RetrofitClient.getInstance(this);
        retroFitClient.startCall();
    }


    @Override
    public void showProducts(List<Product> products) {
        productAdapter.setProducts(products);
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResponseFailure(String msg) {
        Snackbar.make(recyclerView, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(Product product) {
//        localProducts.addProduct(product);
        addToFavorites(product);
        Toast.makeText(this.requireContext(), product.getTitle() + "\nadded to favs", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void addToFavorites(Product product) {
        localProducts.addProduct(product);
    }
}