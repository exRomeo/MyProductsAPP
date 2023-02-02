package com.example.myproductsapp.network;

public interface ClientInterface {
    void fetchProducts(NetworkDelegate networkDelegate);
    void fetchProduct(NetworkDelegate networkDelegate,int id);
}
