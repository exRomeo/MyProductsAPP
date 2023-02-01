package com.example.myproductsapp.network;

import androidx.annotation.NonNull;

import com.example.myproductsapp.ProductView;
import com.example.myproductsapp.localdb.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient retrofitClient;
    List<Product> listOfOne;
    private List<Product> productsList;
    private ServerCalls serverCalls;
    private ProductView productView;

    private RetrofitClient(ProductView productView) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServerCalls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serverCalls = retrofit.create(ServerCalls.class);
        this.productView = productView;
    }

    public static synchronized RetrofitClient getInstance(ProductView productView) {
        if (retrofitClient == null)
            retrofitClient = new RetrofitClient(productView);
        return retrofitClient;
    }


    public void startCall() {

        Call<ProductModel> products = serverCalls.getAllProducts();
        Callback<ProductModel> mProducts = new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<ProductModel> call, Response<ProductModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productsList = response.body().getProducts();
                    productView.showProducts(productsList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProductModel> call, Throwable e) {
                e.printStackTrace();
                productView.showError(e.getMessage());
            }
        };
        products.enqueue(mProducts);
    }

    public void startCall(int id) {
        Call<Product> product = serverCalls.getProduct(id);
        Callback<Product> mProduct = new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Product> call, Response<Product> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listOfOne = new ArrayList<>();
                    listOfOne.add(response.body());
                    productView.showProducts(listOfOne);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Product> call, Throwable e) {
                e.printStackTrace();
                productView.showError(e.getMessage());
            }
        };
        product.enqueue(mProduct);
    }


}
