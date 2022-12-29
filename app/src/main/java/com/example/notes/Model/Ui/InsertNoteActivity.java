package com.example.notes.Model.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.example.notes.R;
import com.example.notes.Services.Model.Notes;
import com.example.notes.ViewModel.NotesViewModel;
import com.example.notes.databinding.ActivityInsertNoteBinding;

import java.util.Date;

public class InsertNoteActivity extends AppCompatActivity {
    ActivityInsertNoteBinding binding;
    String tittle, subTittle, notes;
    NotesViewModel notesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNoteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        binding.doneFloatingActionButton.setOnClickListener(v -> {
            tittle = binding.tittleID.getText().toString();
            subTittle = binding.subTittleID.getText().toString();
            notes = binding.noteID.getText().toString();
            CreatedNotes( tittle, subTittle, notes);
        });
    }

    private void CreatedNotes(String tittle, String subTittle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM,d,YYYY",date.getTime());

        Notes notes1 = new Notes();
        notes1.notesTittle = tittle;
        notes1.notesSubTittle = subTittle;
        notes1.notesDate = notes;
        notes1.notesDate = sequence.toString();
        notesViewModel.insertNote(notes1);
        Toast.makeText(this, "Notes Created Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}