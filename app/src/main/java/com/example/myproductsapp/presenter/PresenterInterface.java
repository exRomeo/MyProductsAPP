package com.example.myproductsapp.presenter;

import androidx.lifecycle.LiveData;

import com.example.myproductsapp.model.Product;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface PresenterInterface {
    void getProducts();
    void getProduct(int id);
    void addToFavorites(Product product);
    Flowable<List<Product>> getFavorites();
    void removeProduct(Product product);
}
