package com.example.myproductsapp.localdb;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.myproductsapp.Repository;

import java.util.ArrayList;
import java.util.List;


public class LocalProducts implements Repository {
    private static LocalProducts productsRepo;
    private final ProductDAO productDAO;
    private final LiveData<List<Product>> storedProducts;
    private List<Product> products;

    Context context;

    private LocalProducts(Context context) {
        this.context = context;
        ProductsDatabase pDB = ProductsDatabase.getInstance(context);
        productDAO = pDB.productDAO();
        storedProducts = productDAO.getAllProducts();
    }

    public static LocalProducts getInstance(Context context) {
        if (productsRepo == null) productsRepo = new LocalProducts(context);
        return productsRepo;
    }

    @Override
    public LiveData<List<Product>> getProducts() {
        return storedProducts;
    }

    @Override
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public void addProduct(Product product) {
        new Thread(() -> productDAO.insertProduct(product)).start();
    }

    @Override
    public void removeProduct(Product product) {
        new Thread(() -> productDAO.deleteProduct(product)).start();
    }
}
