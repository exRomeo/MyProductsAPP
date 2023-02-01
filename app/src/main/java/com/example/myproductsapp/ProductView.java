package com.example.myproductsapp;

import java.util.List;

public interface ProductView {
    void showProducts(List<Product> products);
    void addToFavorites(Product product);
}
