package com.example.myproductsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myproductsapp.productviews.ProductActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button listButton = findViewById(R.id.listButton);
        Button favoritesButton = findViewById(R.id.favsButton);
        Button exitButton = findViewById(R.id.exitButton);
        listButton.setOnClickListener(v->{
            Intent toList = new Intent(this, ProductActivity.class);
            toList.putExtra("dest",1);
            startActivity(toList);
        });
        favoritesButton.setOnClickListener(v->{
            Intent toFavs = new Intent(this,ProductActivity.class);
            toFavs.putExtra("dest",2);
            startActivity(toFavs);
        });
        exitButton.setOnClickListener(v-> finish());
    }
}