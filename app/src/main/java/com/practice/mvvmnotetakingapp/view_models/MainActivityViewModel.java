package com.practice.mvvmnotetakingapp.view_models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.practice.mvvmnotetakingapp.model.NoteEntity;
import com.practice.mvvmnotetakingapp.repository.AppRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private AppRepository appRepository;
    public LiveData<List<NoteEntity>> notesList;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        appRepository = AppRepository.getInstance(application.getApplicationContext());
        notesList = appRepository.notesList;
    }

    public void addSampleData() {
        appRepository.addSampleData();
    }

    public void deleteAllData() {
         appRepository.deleteAllData();
    }
}
