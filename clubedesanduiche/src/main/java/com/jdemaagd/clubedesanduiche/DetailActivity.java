package com.jdemaagd.clubedesanduiche;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.jdemaagd.clubedesanduiche.models.Sandwich;
import com.jdemaagd.clubedesanduiche.utils.JsonUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            closeOnError();

            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];

        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            closeOnError();

            return;
        }

        setTitle(sandwich.getMainName());

        populateUI(sandwich);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void populateUI(Sandwich sandwich) {
        ImageView imageView = findViewById(R.id.iv_image);

        Picasso.get()
                .load(sandwich.getImage())
                .into(imageView);

        TextView originTextView = findViewById(R.id.tv_origin);
        originTextView.setText(sandwich.getPlaceOfOrigin());
        TextView alsoKnownTextView = findViewById(R.id.tv_also_known);
        alsoKnownTextView.setText(String.join(", ", sandwich.getAlsoKnownAs()));
        TextView descTextView = findViewById(R.id.tv_description);
        descTextView.setText(sandwich.getDescription());
        TextView ingredientsTextView = findViewById(R.id.tv_ingredients);
        ingredientsTextView.setText(String.join(", ", sandwich.getIngredients()));
    }
}
