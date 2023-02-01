package com.example.myproductsapp.network;

import com.example.myproductsapp.localdb.Product;

import java.util.List;

public class ProductModel {
    private List<Product> products;

//    public ProductModel(List<Product> productList, int total, int skip, int limit) {
//        this.productList = productList;
//        this.total = total;
//        this.skip = skip;
//        this.limit = limit;
//    }
//
//    private int total;
//    private int skip;
//    private int limit;
//
//    public int getTotal() {
//        return total;
//    }

//    public void setTotal(int total) {
//        this.total = total;
//    }
//
//    public int getSkip() {
//        return skip;
//    }
//
//    public void setSkip(int skip) {
//        this.skip = skip;
//    }
//
//    public int getLimit() {
//        return limit;
//    }
//
//    public void setLimit(int limit) {
//        this.limit = limit;
//    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProductList(List<Product> productList) {
        this.products = productList;
    }
}
