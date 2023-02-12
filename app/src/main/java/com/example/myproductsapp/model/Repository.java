package com.example.myproductsapp.model;

import androidx.lifecycle.LiveData;

import com.example.myproductsapp.network.NetworkDelegate;
import com.example.myproductsapp.localdb.LocalSourceInterface;
import com.example.myproductsapp.network.ClientInterface;
import com.example.myproductsapp.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.rxjava3.core.Flowable;

public class Repository implements RepositoryInterface {
    ClientInterface remoteSource;
    LocalSourceInterface localSource;
    private static Repository repository;


    public static Repository getInstance(RetrofitClient retrofitClient, LocalSourceInterface localProducts) {
        if (repository == null) {
            repository = new Repository(retrofitClient, localProducts);
        }
        return repository;
    }

    private Repository(RetrofitClient retrofitClient, LocalSourceInterface localProducts) {
        this.remoteSource = retrofitClient;
        this.localSource = localProducts;

    }

    @Override
    public Flowable<List<Product>> getProducts() {
        return localSource.getProducts();
    }



    @Override
    public void setProducts(ArrayList<Product> products) {
        localSource.setProducts(products);
    }

    @Override
    public void getAllProducts(NetworkDelegate networkDelegate) {
        remoteSource.fetchProducts(networkDelegate);
    }
    @Override
    public void getProduct(NetworkDelegate networkDelegate, int id) {
        remoteSource.fetchProduct(networkDelegate,id);
    }

    @Override
    public void addProduct(Product product) {
        localSource.addProduct(product);
    }

    @Override
    public void removeProduct(Product product) {
        localSource.removeProduct(product);
    }
}

