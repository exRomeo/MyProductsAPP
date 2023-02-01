package com.example.myproductsapp;

import com.example.myproductsapp.localdb.Product;

public interface ProductPresenter {
    void getProducts();
    void addToFavorites(Product product);
}
