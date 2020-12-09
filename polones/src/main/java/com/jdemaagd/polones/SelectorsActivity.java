package com.jdemaagd.polones;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class SelectorsActivity extends AppCompatActivity
        implements SelectorItemsAdapter.ListItemClickListener{

    private static final String TAG = SelectorsActivity.class.getSimpleName();

    RecyclerView mRecyclerView;
    SelectorItemsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectors_activity);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = findViewById(R.id.mainRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new SelectorItemsAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Log.v(TAG, "List item clicked at index: " + clickedItemIndex);
    }
}