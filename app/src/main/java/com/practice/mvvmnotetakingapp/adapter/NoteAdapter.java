package com.practice.mvvmnotetakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.practice.mvvmnotetakingapp.EditorActivity;
import com.practice.mvvmnotetakingapp.R;
import com.practice.mvvmnotetakingapp.model.NoteEntity;
import com.practice.mvvmnotetakingapp.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.VH> {

    private Context context;
    private List<NoteEntity> arrayListNoteEntities;

    public NoteAdapter(Context context, List<NoteEntity> notesList) {
        this.context = context;
        this.arrayListNoteEntities = notesList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int position) {

        vh.tvNote.setText(arrayListNoteEntities.get(position).getNote());
    }

    @Override
    public int getItemCount() {
        return arrayListNoteEntities.size();
    }

    class VH extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_note)
        TextView tvNote;

        @BindView(R.id.fab_edit_note)
        FloatingActionButton fabEditNote;

        VH(View view) {
            super(view);
            /* Must initialize butter knife before accessing the views*/
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.fab_edit_note)
        public void editNoteOnClick() {
            int noteId = arrayListNoteEntities.get(getAdapterPosition()).getNoteId();
            Intent intent = new Intent(context, EditorActivity.class);
            intent.putExtra(Constants.NOTE_ID_KEY, noteId);
            context.startActivity(intent);
        }
    }
}
