package com.example.myproductsapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductsDatabase extends RoomDatabase {
    private static ProductsDatabase instance;

    public abstract ProductDAO productDAO();

    public static synchronized ProductsDatabase getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(), ProductsDatabase.class, "products-db").build();
        return instance;
    }
}
