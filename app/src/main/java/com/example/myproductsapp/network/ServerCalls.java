package com.example.myproductsapp.network;

import com.example.myproductsapp.model.Product;
import com.example.myproductsapp.model.ProductModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServerCalls {
    String BASE_URL = "https://dummyjson.com/";
    @GET("products")
    Call<ProductModel> getAllProducts();
    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") int id);
}
