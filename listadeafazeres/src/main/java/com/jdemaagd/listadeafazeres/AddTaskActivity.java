package com.jdemaagd.listadeafazeres;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.jdemaagd.listadeafazeres.database.TaskEntry;
import com.jdemaagd.listadeafazeres.database.AppDatabase;

import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {

    private static final String LOG_TAG = AddTaskActivity.class.getSimpleName();

    public static final String EXTRA_TASK_ID = "extraTaskId";
    public static final String INSTANCE_TASK_ID = "instanceTaskId";

    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_MEDIUM = 2;
    public static final int PRIORITY_LOW = 3;

    private static final int DEFAULT_TASK_ID = -1;

    EditText mEditText;
    RadioGroup mRadioGroup;
    Button mButton;

    private int mTaskId = DEFAULT_TASK_ID;

    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        initViews();

        mDb = AppDatabase.getInstance(getApplicationContext());

        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_TASK_ID)) {
            mTaskId = savedInstanceState.getInt(INSTANCE_TASK_ID, DEFAULT_TASK_ID);
        }

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_TASK_ID)) {
            mButton.setText(R.string.update_button);
            if (mTaskId == DEFAULT_TASK_ID) {
                mTaskId = intent.getIntExtra(EXTRA_TASK_ID, DEFAULT_TASK_ID);

                AddTaskViewModelFactory factory = new AddTaskViewModelFactory(mDb, mTaskId);

                final AddTaskViewModel viewModel
                        = new ViewModelProvider(this, factory).get(AddTaskViewModel.class);

                viewModel.getTask().observe(this, new Observer<TaskEntry>() {
                    @Override
                    public void onChanged(@Nullable TaskEntry taskEntry) {
                        viewModel.getTask().removeObserver(this);
                        Log.d(LOG_TAG, "Receiving database update from LiveData.");
                        populateUI(taskEntry);
                    }
                });
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(INSTANCE_TASK_ID, mTaskId);
        super.onSaveInstanceState(outState);
    }

    /**
     * getPriority is called whenever the selected priority needs to be retrieved
     */
    public int getPriorityFromViews() {
        int priority = 1;
        int checkedId = ((RadioGroup) findViewById(R.id.radio_group)).getCheckedRadioButtonId();
        switch (checkedId) {
            case R.id.radio_button1:
                priority = PRIORITY_HIGH;
                break;
            case R.id.radio_button2:
                priority = PRIORITY_MEDIUM;
                break;
            case R.id.radio_button3:
                priority = PRIORITY_LOW;
        }

        return priority;
    }

    /**
     * onSaveButtonClicked is called when the "save" button is clicked
     * It retrieves user input and inserts that new task data into the underlying database
     */
    public void onSaveButtonClicked() {
        String description = mEditText.getText().toString();
        int priority = getPriorityFromViews();
        Date date = new Date();

        final TaskEntry task = new TaskEntry(description, priority, date);
        AppExecutors.getInstance().diskIO().execute(() -> {
            if (mTaskId == DEFAULT_TASK_ID) {
                mDb.taskDao().insertTask(task);
            } else {
                task.setId(mTaskId);
                mDb.taskDao().updateTask(task);
            }
            finish();
        });
    }

    /**
     * setPriority is called when we receive a task from MainActivity
     *
     * @param priority the priority value
     */
    public void setPriorityInViews(int priority) {
        switch (priority) {
            case PRIORITY_HIGH:
                ((RadioGroup) findViewById(R.id.radio_group)).check(R.id.radio_button1);
                break;
            case PRIORITY_MEDIUM:
                ((RadioGroup) findViewById(R.id.radio_group)).check(R.id.radio_button2);
                break;
            case PRIORITY_LOW:
                ((RadioGroup) findViewById(R.id.radio_group)).check(R.id.radio_button3);
        }
    }

    /**
     * initViews is called from onCreate to init the member variable views
     */
    private void initViews() {
        mEditText = findViewById(R.id.et_task_desc);
        mRadioGroup = findViewById(R.id.radio_group);

        mButton = findViewById(R.id.btn_save);
        mButton.setOnClickListener(view -> onSaveButtonClicked());
    }

    /**
     * populateUI would be called to populate the UI when in update mode
     *
     * @param task the taskEntry to populate the UI
     */
    private void populateUI(TaskEntry task) {
        if (task == null) {
            return;
        }

        mEditText.setText(task.getDescription());
        setPriorityInViews(task.getPriority());
    }
}