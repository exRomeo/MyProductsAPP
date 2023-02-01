package com.example.myproductsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproductsapp.localdb.LocalProducts;
import com.example.myproductsapp.localdb.Product;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment implements OnProductClickListener {
    private RecyclerView recyclerView;
    private FavsAdapter favsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private LocalProducts products;

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.favsList);
        favsAdapter = new FavsAdapter(this.getContext(),new ArrayList<>(),this);
        layoutManager = new LinearLayoutManager(this.getContext());
        products = LocalProducts.getInstance(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(favsAdapter);
        products.getProducts().observe(this.requireActivity(), products -> {
            favsAdapter.setProducts(products);
            favsAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onClick(Product product) {
        products.removeProduct(product);
        favsAdapter.notifyDataSetChanged();
        Toast.makeText(this.getContext(), "Rmoved\n" + product.getTitle(), Toast.LENGTH_SHORT).show();
    }
}