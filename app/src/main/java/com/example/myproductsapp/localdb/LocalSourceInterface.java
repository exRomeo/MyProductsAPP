package com.example.myproductsapp.localdb;

import androidx.lifecycle.LiveData;

import com.example.myproductsapp.model.Product;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.rxjava3.core.Flowable;

public interface LocalSourceInterface {
    Flowable<List<Product>> getProducts();

    void setProducts(ArrayList<Product> products);

    void addProduct(Product product);

    void removeProduct(Product product);
}
