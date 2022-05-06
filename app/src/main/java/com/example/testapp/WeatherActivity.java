package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the black title bar
        getSupportActionBar().hide();

        setContentView(R.layout.activity_weather);
    }
}