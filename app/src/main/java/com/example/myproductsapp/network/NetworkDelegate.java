package com.example.myproductsapp.network;

import com.example.myproductsapp.model.Product;

import java.util.List;

public interface NetworkDelegate {
    void onResponseSuccess(List<Product> products);
    void onResponseSuccess(Product product);
    void onResponseFailure(String errorMessage);
}
