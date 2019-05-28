package com.practice.mvvmnotetakingapp.view_models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.practice.mvvmnotetakingapp.model.NoteEntity;
import com.practice.mvvmnotetakingapp.repository.AppRepository;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EditorActivityViewModel extends AndroidViewModel {
    private AppRepository appRepository;
    public MutableLiveData<NoteEntity> mLiveNote = new MutableLiveData<>();
    private Executor executor = Executors.newSingleThreadExecutor();

    public EditorActivityViewModel(@NonNull Application application) {
        super(application);

        appRepository = AppRepository.getInstance(application.getApplicationContext());
    }


    public void loadNote(final int noteId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                NoteEntity noteEntity = appRepository.loadNote(noteId);
                /*use postValue when we are on different or background thread and use setValue method when we are at same thread or main thread*/
                mLiveNote.postValue(noteEntity);
            }
        });
    }

    public void saveNote(String noteText) {
        NoteEntity noteEntity = mLiveNote.getValue();
        if(noteEntity == null)
        {
            if(TextUtils.isEmpty(noteText.trim()))
            {
                return;
            }else
            {
                noteEntity = new NoteEntity(noteText.trim(), new Date());
            }
        }else
        {
            noteEntity.setNote(noteText);
            noteEntity.setDate(new Date());
        }
        appRepository.insertNote(noteEntity);
    }
}
