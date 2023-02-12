package com.example.myproductsapp.network;

import androidx.annotation.NonNull;

import com.example.myproductsapp.model.Product;
import com.example.myproductsapp.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient implements ClientInterface {

    private static RetrofitClient retrofitClient;
    private List<Product> productsList;
    private final ServerCalls serverCalls;


    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServerCalls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        serverCalls = retrofit.create(ServerCalls.class);

    }

    public static synchronized RetrofitClient getInstance() {
        if (retrofitClient == null)
            retrofitClient = new RetrofitClient();

        return retrofitClient;
    }


    public void fetchProducts(NetworkDelegate networkDelegate) {
        Observable<ProductModel> productsObs = serverCalls.getAllProducts();
        Disposable d = productsObs.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(10)
                .subscribe(item -> networkDelegate.onResponseSuccess(item.getProducts()),
                        throwable -> networkDelegate.onResponseFailure(throwable.getMessage()));

    }

    public void fetchProduct(NetworkDelegate networkDelegate, int id) {
        Observable<Product> productsObs = serverCalls.getProduct(id);
        Disposable d = productsObs.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(10)
                .subscribe(item -> networkDelegate.onResponseSuccess(item),
                        throwable -> networkDelegate.onResponseFailure(throwable.getMessage()));
    }


}
