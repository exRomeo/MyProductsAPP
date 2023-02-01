package com.example.myproductsapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServerCalls {
    @GET("products")
    Call<ProductsRepo> getAllProducts();
    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") int id);
}
