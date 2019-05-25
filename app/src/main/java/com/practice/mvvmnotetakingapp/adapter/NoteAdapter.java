package com.practice.mvvmnotetakingapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.practice.mvvmnotetakingapp.model.Note;
import com.practice.mvvmnotetakingapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.VH> {

    private Context context;
    private List<Note> arrayListNotes;

    public NoteAdapter(Context context, List<Note> notesList) {
        this.context = context;
        this.arrayListNotes = notesList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int position) {

        vh.tvNote.setText(arrayListNotes.get(position).getNote());
    }

    @Override
    public int getItemCount() {
        return arrayListNotes.size();
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
    }
}
