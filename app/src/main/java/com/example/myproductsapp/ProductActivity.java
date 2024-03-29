package com.example.myproductsapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ProductActivity extends AppCompatActivity {
    private int productID;
    private int dest;
    private FavoritesFragment favoritesFragment;
    private ProductListFragment productListFragment;
    private ProductFragment productFragment;

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
        if (savedInstanceState == null) {
            switch (dest) {
                case 1:
                    productListFragment = new ProductListFragment();
                    fragmentTransaction.add(R.id.fragmentContainerView, productListFragment, "list");
                    break;
                case 2:
                    favoritesFragment = new FavoritesFragment();
                    fragmentTransaction.add(R.id.fragmentContainerView, favoritesFragment, "favorites");
                    break;
                case 3:
                    productFragment = new ProductFragment();
                    fragmentTransaction.add(R.id.fragmentContainerView, productFragment, "product");
//                  fragmentTransaction.replace(R.id.fragmentContainerView, productFragment);
                    break;
                default:
                    finish();
                    break;
            }
        } else {
            if (favoritesFragment != null)
                favoritesFragment = (FavoritesFragment) getSupportFragmentManager().getFragment(savedInstanceState, "favoritesFragment");
            if (productListFragment != null)
                productListFragment = (ProductListFragment) getSupportFragmentManager().getFragment(savedInstanceState, "listFragment");
            if (productFragment != null)
                productFragment = (ProductFragment) getSupportFragmentManager().getFragment(savedInstanceState, "productFragment");
        }
        fragmentTransaction.commit();
    }


    public int getProductID() {
        return productID;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (favoritesFragment != null)
            getSupportFragmentManager().putFragment(outState, "favoritesFragment", favoritesFragment);
        if (productListFragment != null)
            getSupportFragmentManager().putFragment(outState, "listFragment", productListFragment);
        if (productFragment != null)
            getSupportFragmentManager().putFragment(outState, "productFragment",productFragment);
    }
}