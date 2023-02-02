package com.example.myproductsapp.productviews;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myproductsapp.R;

public class ProductActivity extends AppCompatActivity {
    private int productID;
    private int dest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Intent inComing = getIntent();
        if (inComing != null) {
            dest = inComing.getIntExtra("dest", 0);
            productID = inComing.getIntExtra("product", 0);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (dest) {
            case 1:
                ProductListFragment productListFragment = new ProductListFragment();
                fragmentTransaction.add(R.id.fragmentContainerView, productListFragment, "list");
                break;
            case 2:
                FavoritesFragment favoritesFragment = new FavoritesFragment();
                fragmentTransaction.add(R.id.fragmentContainerView, favoritesFragment, "favorites");
                break;
            case 3:
                ProductFragment productFragment = new ProductFragment();
                fragmentTransaction.add(R.id.fragmentContainerView, productFragment, "product");
                break;
            default:
                finish();
                break;
        }

        fragmentTransaction.commit();
    }


    public int getProductID() {
        return productID;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}