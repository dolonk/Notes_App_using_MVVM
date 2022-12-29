package com.example.notes.Model.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.Model.Ui.MainActivity;
import com.example.notes.R;
import com.example.notes.Services.Model.Notes;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {

    MainActivity mainActivity;
    List<Notes> notes;


    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
    }

    @Override
    public notesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new notesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes_row, parent,false));
    }

    @Override
    public void onBindViewHolder(notesViewHolder holder, int position) {
        Notes note = notes.get(position);
        holder.tittle.setText(note.notesTittle);
        holder.subTittle.setText(note.notesSubTittle);
        holder.dateNotes.setText(note.notesDate);

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class notesViewHolder extends RecyclerView.ViewHolder {
        TextView tittle, subTittle, dateNotes;
        public notesViewHolder(View itemView) {
            super(itemView);
            tittle = itemView.findViewById(R.id.tittleNoteRowID);
            subTittle = itemView.findViewById(R.id.subTittleNoteRowID);
            dateNotes = itemView.findViewById(R.id.dateNoteRowID);
        }
    }

}
