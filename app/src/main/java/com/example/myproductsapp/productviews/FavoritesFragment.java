package com.example.myproductsapp.productviews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproductsapp.presenter.Presenter;
import com.example.myproductsapp.presenter.PresenterInterface;
import com.example.myproductsapp.R;
import com.example.myproductsapp.localdb.LocalSource;
import com.example.myproductsapp.model.Product;
import com.example.myproductsapp.network.RetrofitClient;
import com.example.myproductsapp.model.Repository;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class FavoritesFragment extends Fragment implements OnProductClickListener {
    private ProductAdapter productAdapter;

    PresenterInterface presenter;

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.favsList);
        productAdapter = new ProductAdapter(this.getContext(), new ArrayList<>(), this, ContextCompat.getDrawable(this.requireContext(), R.drawable.baseline_delete_forever_24));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        presenter = new Presenter(null, Repository.getInstance(RetrofitClient.getInstance(), LocalSource.getInstance(this.requireContext())));

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(productAdapter);

        Disposable d = presenter.getFavorites().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {
            productAdapter.setProducts(item);
            productAdapter.notifyDataSetChanged();
        });

    }

    @Override
    public void onClick(Product product) {
        presenter.removeProduct(product);
        productAdapter.notifyDataSetChanged();
        Toast.makeText(this.getContext(), "Rmoved\n" + product.getTitle(), Toast.LENGTH_SHORT).show();
    }
}