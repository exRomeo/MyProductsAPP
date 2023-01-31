package com.example.myproductsapp;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;


public class ProductsModel {
    private static ProductsModel productsModel;
    private final ProductDAO productDAO;
    private final LiveData<List<Product>> storedProducts;
        private List<Product> products;

    Context context;

    private ProductsModel(Context context) {
        this.context = context;
        ProductsDatabase pDB = ProductsDatabase.getInstance(context);
        productDAO = pDB.productDAO();
        storedProducts = productDAO.getAllProducts();
    }

    public static ProductsModel getInstance(Context context) {
        if (productsModel == null) productsModel = new ProductsModel(context);
        return productsModel;
    }

    public LiveData<List<Product>> getProducts() {
        return storedProducts;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
    public List<Product> getAllProducts(){
        return products;
    }
    public void addProduct(Product product) {
        new Thread(() -> productDAO.insertProduct(product)).start();
    }

    public void removeProduct(Product product) {
        new Thread(() -> productDAO.deleteProduct(product)).start();
    }
}
