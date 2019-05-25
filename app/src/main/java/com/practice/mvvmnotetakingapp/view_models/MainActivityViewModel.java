package com.practice.mvvmnotetakingapp.view_models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.practice.mvvmnotetakingapp.model.Note;
import com.practice.mvvmnotetakingapp.repository.AppRepository;
import com.practice.mvvmnotetakingapp.repository.SampleDataProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private AppRepository appRepository;
    public List<Note> notesList;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        appRepository = AppRepository.getInstance(application.getApplicationContext());
        notesList = appRepository.notesList;
    }

    public void addSampleData() {
        appRepository.addSampleData();
    }
}
