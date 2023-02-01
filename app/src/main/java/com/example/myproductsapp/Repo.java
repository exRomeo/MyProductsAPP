package com.example.myproductsapp;

import android.content.Context;

import com.example.myproductsapp.localdb.LocalProducts;
import com.example.myproductsapp.network.RetrofitClient;

public class Repo {
    private Context context;
    RetrofitClient remoteSource;
    LocalProducts localSource;
    private static Repo repo;
    public static Repo getInstance(RetrofitClient retrofitClient, LocalProducts localProducts,Context context){
        if(repo == null){
            repo = new Repo(retrofitClient, localProducts,context);
        }
        return repo;
    }
    private Repo(RetrofitClient retrofitClient, LocalProducts localProducts,Context context){
        this.remoteSource = retrofitClient;
        this.localSource = localProducts;
        this.context = context;
    }

}

