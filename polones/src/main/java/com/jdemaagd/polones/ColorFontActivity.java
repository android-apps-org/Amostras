package com.jdemaagd.polones;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ColorFontActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_font_activity);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}