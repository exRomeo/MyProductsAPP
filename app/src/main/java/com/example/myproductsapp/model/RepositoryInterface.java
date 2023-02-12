package com.example.myproductsapp.model;

import androidx.lifecycle.LiveData;

import com.example.myproductsapp.network.NetworkDelegate;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface RepositoryInterface {
    Flowable<List<Product>> getProducts();
     void getProduct(NetworkDelegate networkDelegate, int id);

    void setProducts(ArrayList<Product> products);

    void getAllProducts(NetworkDelegate networkDelegate);

    void addProduct(Product product);

    void removeProduct(Product product);
}
