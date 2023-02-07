package com.example.myproductsapp.network;

import androidx.annotation.NonNull;

import com.example.myproductsapp.model.Product;
import com.example.myproductsapp.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient implements ClientInterface{

    private static RetrofitClient retrofitClient;
    private List<Product> listOfOne;
    private List<Product> productsList;
    private final ServerCalls serverCalls;


    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServerCalls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serverCalls = retrofit.create(ServerCalls.class);

    }

    public static synchronized RetrofitClient getInstance() {
        if (retrofitClient == null)
            retrofitClient = new RetrofitClient();

        return retrofitClient;
    }


    public void fetchProducts(NetworkDelegate networkDelegate) {

        Call<ProductModel> products = serverCalls.getAllProducts();
        Callback<ProductModel> mProducts = new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<ProductModel> call, Response<ProductModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productsList = response.body().getProducts();
                    networkDelegate.onResponseSuccess(productsList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProductModel> call, Throwable e) {
                e.printStackTrace();
                networkDelegate.onResponseFailure(e.getMessage());
            }
        };
        products.enqueue(mProducts);
    }

    public void fetchProduct(NetworkDelegate networkDelegate,int id) {
        Call<Product> product = serverCalls.getProduct(id);
        Callback<Product> mProduct = new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Product> call, Response<Product> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listOfOne = new ArrayList<>();
                    listOfOne.add(response.body());
                    networkDelegate.onResponseSuccess(listOfOne);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Product> call, Throwable e) {
                e.printStackTrace();
                networkDelegate.onResponseFailure(e.getMessage());
            }
        };
        product.enqueue(mProduct);
    }


}
