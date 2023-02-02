package com.example.myproductsapp.localdb;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.myproductsapp.model.Product;

import java.util.ArrayList;
import java.util.List;


 public class LocalSource implements LocalSourceInterface {
    private static LocalSourceInterface localSource;
    private final ProductDAO productDAO;
    private final LiveData<List<Product>> storedProducts;
    Context context;

    private LocalSource(Context context) {
        this.context = context;
        ProductsDatabase pDB = ProductsDatabase.getInstance(context);
        productDAO = pDB.productDAO();
        storedProducts = productDAO.getAllProducts();
    }

    public static LocalSourceInterface getInstance(Context context) {
        if (localSource == null) localSource = new LocalSource(context);
        return localSource;
    }

    @Override
    public LiveData<List<Product>> getProducts() {
        return storedProducts;
    }

    @Override
    public void setProducts(ArrayList<Product> products) {

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
