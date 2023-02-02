package com.example.myproductsapp.model;

import java.util.List;

public class ProductModel {
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProductList(List<Product> productList) {
        this.products = productList;
    }
}
