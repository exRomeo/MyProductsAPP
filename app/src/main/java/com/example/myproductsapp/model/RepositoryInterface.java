package com.example.myproductsapp.model;

import androidx.lifecycle.LiveData;

import com.example.myproductsapp.network.NetworkDelegate;

import java.util.ArrayList;
import java.util.List;

public interface RepositoryInterface {
    LiveData<List<Product>> getProducts();
     void getProduct(NetworkDelegate networkDelegate, int id);

    void setProducts(ArrayList<Product> products);

    void getAllProducts(NetworkDelegate networkDelegate);

    void addProduct(Product product);

    void removeProduct(Product product);
}
