package com.example.myproductsapp.presenter;

import androidx.lifecycle.LiveData;

import com.example.myproductsapp.model.Product;

import java.util.List;

public interface PresenterInterface {
    void getProducts();
    void getProduct(int id);
    void addToFavorites(Product product);
    LiveData<List<Product>> getFavorites();
    void removeProduct(Product product);
}
