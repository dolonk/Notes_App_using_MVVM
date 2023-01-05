package com.example.notes.Model.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
    String tittle, subTittle, notes, pickDate;
    NotesViewModel notesViewModel;
    String priority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNoteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        binding.greenPriorityID.setOnClickListener(v -> {
            binding.greenPriorityID.setImageResource(R.drawable.ic_baseline_done_24);
            binding.yellowPriorityID.setImageResource(0);
            binding.redPriorityID.setImageResource(0);
            priority = "1";
        });

        binding.yellowPriorityID.setOnClickListener(v -> {
            binding.greenPriorityID.setImageResource(0);
            binding.yellowPriorityID.setImageResource(R.drawable.ic_baseline_done_24);
            binding.redPriorityID.setImageResource(0);
            priority = "2";
        });

        binding.redPriorityID.setOnClickListener(v -> {
            binding.greenPriorityID.setImageResource(0);
            binding.yellowPriorityID.setImageResource(0);
            binding.redPriorityID.setImageResource(R.drawable.ic_baseline_done_24);
            priority = "3";
        });

        binding.doneFloatingActionButton.setOnClickListener(v -> {

            tittle = binding.tittleID.getText().toString();
            subTittle = binding.subTittleID.getText().toString();
            notes = binding.noteID.getText().toString();

            if (TextUtils.isEmpty(tittle)) {
                Toast.makeText(InsertNoteActivity.this, "Please Enter Tittle Name", Toast.LENGTH_SHORT).show();
                binding.tittleID.setError("Tittle is requirement");
            } else if (TextUtils.isEmpty(notes)) {
                Toast.makeText(InsertNoteActivity.this, "Please Enter notes data", Toast.LENGTH_SHORT).show();
                binding.noteID.setError("Note is Required");
                binding.noteID.requestFocus();
            } else {
                CreatedNotes(tittle, subTittle, notes, pickDate);
            }
        });


    }

    private void CreatedNotes(String tittle, String subTittle, String notes, String pickDate) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("d MMMM,yyyy", date.getTime());

        Notes insertNotes = new Notes();
        insertNotes.notesTittle = tittle;
        insertNotes.notesSubTittle = subTittle;
        insertNotes.notesPriority = priority;
        insertNotes.notes = notes;
        insertNotes.notesDate = pickDate;
        insertNotes.notesDate = sequence.toString();
        notesViewModel.insertNote(insertNotes);
        Toast.makeText(this, "Notes Created Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}