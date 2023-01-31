package com.example.myproductsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button listButton;
    private Button favsButton;
    private Button exitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listButton = findViewById(R.id.listButton);
        favsButton = findViewById(R.id.favsButton);
        exitButton = findViewById(R.id.exitButton);
        listButton.setOnClickListener(v->{
            Intent toList = new Intent(this,ProductActivity.class);
            toList.putExtra("dest",1);
            startActivity(toList);
        });
        favsButton.setOnClickListener(v->{
            Intent toFavs = new Intent(this,ProductActivity.class);
            toFavs.putExtra("dest",2);
            startActivity(toFavs);
        });
        exitButton.setOnClickListener(v-> finish());
    }
}