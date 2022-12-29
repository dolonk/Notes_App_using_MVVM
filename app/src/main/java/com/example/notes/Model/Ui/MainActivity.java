package com.example.notes.Model.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.notes.Model.Adapter.NotesAdapter;
import com.example.notes.R;
import com.example.notes.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    NotesViewModel notesViewModel;
    RecyclerView notesRecyclerView;
    NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        notesRecyclerView = findViewById(R.id.recyclerViewID);

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        floatingActionButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,InsertNoteActivity.class));
        });
        notesViewModel.getAllNotes.observe(this,notes -> {
            notesRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
            notesAdapter = new NotesAdapter(MainActivity.this,notes);
            notesRecyclerView.setAdapter(notesAdapter);

        });
    }
}