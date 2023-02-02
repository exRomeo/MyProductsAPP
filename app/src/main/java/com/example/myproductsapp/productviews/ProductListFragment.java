package com.example.myproductsapp.productviews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproductsapp.presenter.Presenter;
import com.example.myproductsapp.presenter.PresenterInterface;
import com.example.myproductsapp.R;
import com.example.myproductsapp.localdb.LocalSource;
import com.example.myproductsapp.model.Product;
import com.example.myproductsapp.network.RetrofitClient;
import com.example.myproductsapp.model.Repository;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class ProductListFragment extends Fragment implements ProductViewInterface, OnProductClickListener {
    private RecyclerView recyclerView;
    private ImageView holder;
    private ProductAdapter productAdapter;
    private PresenterInterface presenter;

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
        productAdapter = new ProductAdapter(this.requireContext(), new ArrayList<>(), this, ContextCompat.getDrawable(this.requireContext(),R.drawable.heart));
        presenter = new Presenter(this, Repository.getInstance(RetrofitClient.getInstance(), LocalSource.getInstance(this.requireContext())));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.requireContext()));
        recyclerView.setAdapter(productAdapter);
        presenter.getProducts();
    }


    @Override
    public void showProducts(List<Product> products) {
        productAdapter.setProducts(products);
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {
        Snackbar.make(recyclerView, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(Product product) {
        addProduct(product);
        Toast.makeText(this.requireContext(), product.getTitle() + "\nadded to favs", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addProduct(Product product) {
        presenter.addToFavorites(product);
    }

}