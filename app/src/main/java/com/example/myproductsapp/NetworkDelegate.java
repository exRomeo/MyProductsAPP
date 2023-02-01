package com.example.myproductsapp;

import com.example.myproductsapp.localdb.Product;

import java.util.List;

public interface NetworkDelegate {
    void onResponseSuccess(List<Product> products);
    void onResponseFailure(String errorMessage);
}
