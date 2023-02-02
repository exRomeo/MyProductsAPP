package com.example.myproductsapp.productviews;

import com.example.myproductsapp.model.Product;

import java.util.List;

public interface ProductViewInterface {

    void showProducts(List<Product> products);
    void addProduct(Product product);

    void showError(String msg);

}
