package com.example.myproductsapp.model;

import android.icu.text.NumberFormat;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.Locale;
@Entity(tableName = "products")
public class Product {
    @PrimaryKey
    @ColumnInfo
    private final int id;

    private final String title;

    private final String description;
    private  final int price;
    private  final float discountPercentage;
    private  final float rating;
    private  final int stock;
    private  final String brand;
    private  final String category;
    private  final String thumbnail;
    @Ignore
    private  List<String> images;

    public Product(int id, String title, String description, int price, float discountPercentage, float rating, int stock, String brand, String category, String thumbnail) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.rating = rating;
        this.stock = stock;
        this.brand = brand;
        this.category = category;
        this.thumbnail = thumbnail;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTextPrice() {
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(price);
    }

    public int getPrice() {
        return price;
    }

    public float getDiscountPercentage() {
        return discountPercentage;
    }

    public float getRating() {
        return rating;
    }

    public int getStock() {
        return stock;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public List<String> getImages() {
        return images;
    }
}