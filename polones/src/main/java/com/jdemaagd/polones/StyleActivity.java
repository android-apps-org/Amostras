package com.jdemaagd.polones;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class StyleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.style_activity);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}