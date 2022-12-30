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
import com.example.notes.databinding.ActivityUpdateNoteBinding;

import java.util.Date;

public class UpdateNoteActivity extends AppCompatActivity {
    ActivityUpdateNoteBinding binding;
    int uId;
    String uTittle, uSubTittle, uPriority, uNotes;
    NotesViewModel notesViewModel;
    String priority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNoteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        uId = getIntent().getIntExtra("ID", 0);
        uTittle = getIntent().getStringExtra("TITTLE");
        uSubTittle = getIntent().getStringExtra("SUB_TITTLE");
        uPriority = getIntent().getStringExtra("PRIORITY");
        uNotes = getIntent().getStringExtra("NOTES");

        binding.updateTittleID.setText(uTittle);
        binding.updateSubTittleID.setText(uSubTittle);
        binding.updateNoteID.setText(uNotes);

        if (uPriority.equals("1")) {
            binding.updateGreenPriorityID.setImageResource(R.drawable.ic_baseline_done_24);
        } else if (uPriority.equals("2")) {
            binding.updateYellowPriorityID.setImageResource(R.drawable.ic_baseline_done_24);
        } else if (uPriority.equals("3")) {
            binding.updateRedPriorityID.setImageResource(R.drawable.ic_baseline_done_24);
        }

        binding.updateGreenPriorityID.setOnClickListener(v -> {
            binding.updateGreenPriorityID.setImageResource(R.drawable.ic_baseline_done_24);
            binding.updateYellowPriorityID.setImageResource(0);
            binding.updateRedPriorityID.setImageResource(0);
            priority = "1";
        });

        binding.updateYellowPriorityID.setOnClickListener(v -> {
            binding.updateGreenPriorityID.setImageResource(0);
            binding.updateYellowPriorityID.setImageResource(R.drawable.ic_baseline_done_24);
            binding.updateRedPriorityID.setImageResource(0);
            priority = "2";
        });

        binding.updateRedPriorityID.setOnClickListener(v -> {
            binding.updateGreenPriorityID.setImageResource(0);
            binding.updateYellowPriorityID.setImageResource(0);
            binding.updateRedPriorityID.setImageResource(R.drawable.ic_baseline_done_24);
            priority = "3";
        });

        binding.updateDoneFloatingActionButton.setOnClickListener(v -> {
            String tittle = binding.updateTittleID.getText().toString();
            String subTittle = binding.updateSubTittleID.getText().toString();
            String priority = binding.updatePriorityID.getText().toString();
            String notes = binding.updateNoteID.getText().toString();
            UpdateNotes(tittle, subTittle, notes);
        });
    }

    private void UpdateNotes(String tittle, String subTittle, String notes) {
        Date date = new Date();
        CharSequence sequence = DateFormat.format("d MMMM,yyyy", date.getTime());

        Notes updateNotes = new Notes();
        updateNotes.id = uId;
        updateNotes.notesTittle = tittle;
        updateNotes.notesSubTittle = subTittle;
        updateNotes.notesPriority = priority;
        updateNotes.notes = notes;
        updateNotes.notesDate = notes;
        updateNotes.notesDate = sequence.toString();
        notesViewModel.updateNote(updateNotes);
        Toast.makeText(this, "Notes Update Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}