package com.example.myproductsapp;

import androidx.lifecycle.LiveData;

import com.example.myproductsapp.localdb.Product;

import java.util.ArrayList;
import java.util.List;

public interface Repository {
    LiveData<List<Product>> getProducts();

    void setProducts(ArrayList<Product> products);

    List<Product> getAllProducts();

    void addProduct(Product product);

    void removeProduct(Product product);
}
