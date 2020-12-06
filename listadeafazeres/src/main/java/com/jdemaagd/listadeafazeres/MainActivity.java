package com.jdemaagd.listadeafazeres;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jdemaagd.listadeafazeres.database.AppDatabase;
import com.jdemaagd.listadeafazeres.database.TaskEntry;

import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class MainActivity extends AppCompatActivity implements TaskAdapter.ItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private TaskAdapter mAdapter;

    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_tasks);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new TaskAdapter(this, this);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        mRecyclerView.addItemDecoration(decoration);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                AppExecutors.getInstance().diskIO().execute(() -> {
                    int position = viewHolder.getAdapterPosition();
                    List<TaskEntry> tasks = mAdapter.getTasks();
                    mDb.taskDao().deleteTask(tasks.get(position));
                });
            }
        }).attachToRecyclerView(mRecyclerView);

        FloatingActionButton fabButton = findViewById(R.id.fab);

        fabButton.setOnClickListener(view -> {
            Intent addTaskIntent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivity(addTaskIntent);
        });

        mDb = AppDatabase.getInstance(getApplicationContext());
        setupViewModel();
    }

    private void setupViewModel() {
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getTasks().observe(this, new Observer<List<TaskEntry>>() {
            @Override
            public void onChanged(@Nullable List<TaskEntry> taskEntries) {
                Log.d(TAG, "Updating list of tasks from LiveData in ViewModel");
                mAdapter.setTasks(taskEntries);
            }
        });
    }

    @Override
    public void onItemClickListener(int itemId) {
        Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
        intent.putExtra(AddTaskActivity.EXTRA_TASK_ID, itemId);
        startActivity(intent);
    }
}