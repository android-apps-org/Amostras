package com.jdemaagd.polones;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView colorAndFont = findViewById(R.id.colorAndFont);
        TextView style = findViewById(R.id.style);
        TextView responsiveLayouts = findViewById(R.id.responsiveLayouts);
        TextView touchSelector = findViewById(R.id.touchSelector);

        assert colorAndFont != null;
        colorAndFont.setOnClickListener(v -> {
            Intent numbersIntent = new Intent(MainActivity.this, ColorFontActivity.class);
            startActivity(numbersIntent);
        });

        assert style != null;
        style.setOnClickListener(v -> {
            Intent familyIntent = new Intent(MainActivity.this, StyleActivity.class);
            startActivity(familyIntent);
        });

        assert responsiveLayouts != null;
        responsiveLayouts.setOnClickListener(v -> {
            Intent familyIntent = new Intent(MainActivity.this, ResponsiveLayoutActivity.class);
            startActivity(familyIntent);
        });

        assert touchSelector != null;
        touchSelector.setOnClickListener(v -> {
            Intent colorsIntent = new Intent(MainActivity.this, SelectorsActivity.class);
            startActivity(colorsIntent);
        });
    }
}