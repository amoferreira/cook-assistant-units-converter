package com.example.converter;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setIcon(R.drawable.app_icon);
            bar.setTitle("Converter");
            bar.setDisplayShowHomeEnabled(true);
        }
        setContentView(R.layout.activity_main);
        //findViewById(R.id.start_bt).setOnClickListener(this::btStartOnClick);
        findViewById(R.id.start_massVolumeConverter_bt).setOnClickListener(this::btMassVolumeConverterOnClick);
        findViewById(R.id.start_temperatureConverter_bt).setOnClickListener(this::btTemperatureConverterOnClick);


    }
    public void btMassVolumeConverterOnClick(View view) {
        Intent intent = new Intent(this, MassVolumeConverterActivity.class);
        startActivity(intent);
    }
    public void btTemperatureConverterOnClick(View view) {
        Intent intent= new Intent(this, TemperatureConverterActivity.class);
        startActivity(intent);
    }
}