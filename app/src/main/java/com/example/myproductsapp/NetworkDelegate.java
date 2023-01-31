package com.example.myproductsapp;

import java.util.List;

public interface NetworkDelegate {

    void onResponseSuccess(List<Product> productList);

    void onResponseFailure(String msg);

}
