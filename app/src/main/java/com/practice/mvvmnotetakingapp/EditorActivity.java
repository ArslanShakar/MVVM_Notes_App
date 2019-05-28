package com.practice.mvvmnotetakingapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.practice.mvvmnotetakingapp.model.NoteEntity;
import com.practice.mvvmnotetakingapp.utils.Constants;
import com.practice.mvvmnotetakingapp.view_models.EditorActivityViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditorActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etNote)
    EditText etNote;

    private EditorActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_check);
        initViewModel();
    }

    public void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(EditorActivityViewModel.class);
        viewModel.mLiveNote.observe(EditorActivity.this, new Observer<NoteEntity>() {
            @Override
            public void onChanged(@Nullable NoteEntity noteEntity) {
                if (noteEntity != null) {
                    etNote.setText(noteEntity.getNote());
                    etNote.setSelection(etNote.getText().length());
                }
            }
        });

        if (getIntent().getExtras() == null) {
            //add new note
            setTitle(R.string.add_new_note);
        } else {
            setTitle(R.string.edit_note);
            int noteId = getIntent().getIntExtra(Constants.NOTE_ID_KEY, -1);
            viewModel.loadNote(noteId);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        saveNoteAndExist();
        return super.onSupportNavigateUp();
    }

    private void saveNoteAndExist() {
        viewModel.saveNote(etNote.getText().toString());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveNoteAndExist();
    }
}
