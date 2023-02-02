package com.example.myproductsapp.presenter;

import androidx.lifecycle.LiveData;

import com.example.myproductsapp.productviews.ProductViewInterface;
import com.example.myproductsapp.model.Product;
import com.example.myproductsapp.network.NetworkDelegate;
import com.example.myproductsapp.model.Repository;

import java.util.List;

public class Presenter implements PresenterInterface, NetworkDelegate {
    private final ProductViewInterface productView;
    private final Repository repository;

    public Presenter(ProductViewInterface onlineProductView, Repository repository) {
        this.productView = onlineProductView;
        this.repository = repository;
    }

    @Override
    public void getProducts() {
        repository.getAllProducts(this);
    }

    @Override
    public void getProduct(int id) {
        repository.getProduct(this, id);
    }
    public LiveData<List<Product>> getFavorites(){
        return repository.getProducts();
    }

    @Override
    public void removeProduct(Product product) {
        repository.removeProduct(product);
    }

    @Override
    public void addToFavorites(Product product) {
        repository.addProduct(product);
    }

    @Override
    public void onResponseSuccess(List<Product> products) {
        productView.showProducts(products);
    }

    @Override
    public void onResponseFailure(String errorMessage) {
        productView.showError(errorMessage);
    }
}
