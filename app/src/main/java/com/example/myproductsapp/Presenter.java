package com.example.myproductsapp;

import com.example.myproductsapp.localdb.Product;

import java.util.List;

public class Presenter implements ProductPresenter, NetworkDelegate {
    private ProductView view;
    private Repository repository;

    public Presenter(ProductView view, Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void getProducts() {
        repository.getAllProducts();
    }

    @Override
    public void addToFavorites(Product product) {
        repository.addProduct(product);
    }
    @Override
    public void onResponseSuccess(List<Product> products) {
        view.showProducts(products);
    }

    @Override
    public void onResponseFailure(String errorMessage) {
        view.showError(errorMessage);
    }
}
