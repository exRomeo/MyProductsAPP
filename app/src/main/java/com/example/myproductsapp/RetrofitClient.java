package com.example.myproductsapp;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private List<Product> productsList;
    private ServerCalls serverCalls;
    private static final String BASE_URL = "https://dummyjson.com/";


NetworkDelegate networkDelegate;
    public RetrofitClient(NetworkDelegate networkDelegate) {

        this.networkDelegate =networkDelegate;
    }

    void startCall() {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        serverCalls = retrofit.create(ServerCalls.class);

        Call<ProductsModel> products = serverCalls.getAllProducts();

        Callback<ProductsModel> mProducts = new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<ProductsModel> call, Response<ProductsModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productsList = response.body().getAllProducts();
                    networkDelegate.onResponseSuccess(productsList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProductsModel> call, Throwable e) {
                e.printStackTrace();
                networkDelegate.onResponseFailure(e.getMessage());
            }
        };
        products.enqueue(mProducts);
    }

    void startCall(int id) {
        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        serverCalls = retrofit.create(ServerCalls.class);

        Call<Product> product = serverCalls.getProduct(id);

        Callback<Product> mProduct = new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Product> call, Response<Product> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ArrayList<Product> listOfOne = new ArrayList<>();
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
