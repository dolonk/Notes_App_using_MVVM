package com.example.notes.Model.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.Model.Ui.MainActivity;
import com.example.notes.Model.Ui.UpdateNoteActivity;
import com.example.notes.R;
import com.example.notes.Services.Model.Notes;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {

    MainActivity mainActivity;
    List<Notes> notes;
    List<Notes> searchNotesItem;


    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
        searchNotesItem = new ArrayList<>(notes);
    }

    public void searchNotes(List<Notes> searchbar) {
        this.notes = searchbar;
        notifyDataSetChanged();
    }

    @Override
    public notesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new notesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes_row, parent, false));
    }

    @Override
    public void onBindViewHolder(notesViewHolder holder, int position) {
        Notes note = notes.get(position);

        if (note.notesPriority.equals("1")) {
            holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
        } else if (note.notesPriority.equals("2")) {
            holder.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
        } else if (note.notesPriority.equals("3")) {
            holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
        }

        holder.tittle.setText(note.notesTittle);
        holder.subTittle.setText(note.notesSubTittle);
        holder.dateNotes.setText(note.notesDate);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mainActivity, UpdateNoteActivity.class);
            intent.putExtra("ID", note.id);
            intent.putExtra("TITTLE", note.notesTittle);
            intent.putExtra("SUB_TITTLE", note.notesSubTittle);
            intent.putExtra("PRIORITY", note.notesPriority);
            intent.putExtra("NOTES", note.notes);
            mainActivity.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class notesViewHolder extends RecyclerView.ViewHolder {
        TextView tittle, subTittle, dateNotes;
        View notesPriority;

        public notesViewHolder(View itemView) {
            super(itemView);
            tittle = itemView.findViewById(R.id.tittleNoteRowID);
            subTittle = itemView.findViewById(R.id.subTittleNoteRowID);
            dateNotes = itemView.findViewById(R.id.dateNoteRowID);
            notesPriority = itemView.findViewById(R.id.priorityNoteRowID);
        }
    }

}
