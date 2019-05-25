package com.practice.mvvmnotetakingapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.practice.mvvmnotetakingapp.adapter.NoteAdapter;
import com.practice.mvvmnotetakingapp.model.Note;
import com.practice.mvvmnotetakingapp.view_models.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.layout_activity_main)
    View layout_main_activity;
    //ConstraintLayout layout_main_activity;

    @BindView(R.id.recyclerViewNotes)
    RecyclerView recyclerView;
    private List<Note> arrayListNotes = new ArrayList<>();

    @OnClick(R.id.fab_add_note)
    void fabAddNote() {
        Intent intent = new Intent(this, EditorActivity.class);
        startActivity(intent);
    }

    private MainActivityViewModel mainActivityViewModel;


    /*****************    onCreate  ********************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            getSupportActionBar().setTitle(R.string.notes);
        } catch (Exception ignored) {
        }

        /*
         * Must Initialize the Butter knife before accessing any  other wise we get an exception.
         */
        ButterKnife.bind(this);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        arrayListNotes = mainActivityViewModel.notesList;

        //arrayListNotes = SampleDataProvider.getSampleNotesData();

        initRecyclerView();
    }

    /*****************    Init RecyclerView  ********************/
    private void initRecyclerView() {
        NoteAdapter adapter = new NoteAdapter(this, arrayListNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /*****************      ********************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*****************      ********************/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mAddSampleData:
                getSampleData();
                return true;
            case R.id.mDeleteAllData:
                deleteAllData();
                return true;
            default:
                return false;
        }
    }

    private void deleteAllData() {

    }

    private void getSampleData() {
        mainActivityViewModel.addSampleData();
    }
    /*****************      ********************/


}
