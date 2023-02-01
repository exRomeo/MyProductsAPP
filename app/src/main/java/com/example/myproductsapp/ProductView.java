package com.example.myproductsapp;

import com.example.myproductsapp.localdb.Product;

import java.util.List;

public interface ProductView {

    void showProducts(List<Product> products);

    void onResponseFailure(String msg);

    void addToFavorites(Product product);

}
