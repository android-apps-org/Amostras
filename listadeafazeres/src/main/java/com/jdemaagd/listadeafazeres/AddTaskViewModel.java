package com.jdemaagd.listadeafazeres;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.jdemaagd.listadeafazeres.database.AppDatabase;
import com.jdemaagd.listadeafazeres.database.TaskEntry;

public class AddTaskViewModel extends ViewModel {

    private LiveData<TaskEntry> task;

    public AddTaskViewModel(AppDatabase database, int taskId) {
        task = database.taskDao().loadTaskById(taskId);
    }

    public LiveData<TaskEntry> getTask() {
        return task;
    }
}