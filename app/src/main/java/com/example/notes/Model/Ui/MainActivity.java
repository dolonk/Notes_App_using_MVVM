package com.example.notes.Model.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.notes.Model.Adapter.NotesAdapter;
import com.example.notes.R;
import com.example.notes.Services.Model.Notes;
import com.example.notes.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    NotesViewModel notesViewModel;
    RecyclerView notesRecyclerView;
    NotesAdapter notesAdapter;
    TextView noFiler, highFilter, lowFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        notesRecyclerView = findViewById(R.id.recyclerViewID);
        noFiler = findViewById(R.id.noFilterID);
        highFilter = findViewById(R.id.highToLowID);
        lowFilter = findViewById(R.id.lowToHighID);

        noFiler.setBackgroundResource(R.drawable.filter_selected_shape);
        noFiler.setOnClickListener(v -> {
            loadData(0);
            noFiler.setBackgroundResource(R.drawable.filter_selected_shape);
            highFilter.setBackgroundResource(R.drawable.filter_round_shape);
            lowFilter.setBackgroundResource(R.drawable.filter_round_shape);
        });
        highFilter.setOnClickListener(v -> {
            loadData(1);
            noFiler.setBackgroundResource(R.drawable.filter_round_shape);
            highFilter.setBackgroundResource(R.drawable.filter_selected_shape);
            lowFilter.setBackgroundResource(R.drawable.filter_round_shape);
        });
        lowFilter.setOnClickListener(v -> {
            loadData(2);
            noFiler.setBackgroundResource(R.drawable.filter_round_shape);
            highFilter.setBackgroundResource(R.drawable.filter_round_shape);
            lowFilter.setBackgroundResource(R.drawable.filter_selected_shape);
        });

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        floatingActionButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, InsertNoteActivity.class));
        });
        notesViewModel.getAllNotes.observe(this, notes -> {
            setNotesAdapter(notes);

        });

    }

    private void loadData(int i) {
        if (i == 0) {
            notesViewModel.getAllNotes.observe(this, notes -> {
                setNotesAdapter(notes);

            });
        } else if (i == 1) {
            notesViewModel.highToLow.observe(this, notes -> {
                setNotesAdapter(notes);

            });
        } else if (i == 2) {
            notesViewModel.lowToHigh.observe(this, notes -> {
                setNotesAdapter(notes);

            });
        }
    }

    public void setNotesAdapter(List<Notes> notes) {
        notesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        notesAdapter = new NotesAdapter(MainActivity.this, notes);
        notesRecyclerView.setAdapter(notesAdapter);
    }
}