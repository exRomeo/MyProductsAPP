package com.example.myproductsapp.network;

import com.example.myproductsapp.model.Product;
import com.example.myproductsapp.model.ProductModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServerCalls {
    String BASE_URL = "https://dummyjson.com/";
    @GET("products")
    Observable<ProductModel> getAllProducts();
    @GET("products/{id}")
    Observable<Product> getProduct(@Path("id") int id);
}
