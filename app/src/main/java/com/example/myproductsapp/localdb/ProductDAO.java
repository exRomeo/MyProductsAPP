package com.example.myproductsapp.localdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myproductsapp.model.Product;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface ProductDAO {
    @Query("SELECT * FROM products")
    Flowable<List<Product>> getAllProducts();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProduct(Product product);

    @Delete
    void deleteProduct(Product product);
}
