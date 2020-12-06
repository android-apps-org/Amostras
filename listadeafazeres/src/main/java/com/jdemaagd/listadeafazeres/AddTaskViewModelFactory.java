package com.jdemaagd.listadeafazeres;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.jdemaagd.listadeafazeres.database.AppDatabase;

public class AddTaskViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final AppDatabase mDb;
    private final int mTaskId;

    public AddTaskViewModelFactory(AppDatabase database, int taskId) {
        mDb = database;
        mTaskId = taskId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new AddTaskViewModel(mDb, mTaskId);
    }
}