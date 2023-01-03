package com.example.notes.Model.Ui;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.notes.Model.Adapter.NotesAdapter;
import com.example.notes.R;
import com.example.notes.Services.Model.Notes;
import com.example.notes.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    NotesViewModel notesViewModel;
    RecyclerView notesRecyclerView;
    NotesAdapter notesAdapter;
    TextView noFiler, highFilter, lowFilter;
    List<Notes> filterNoteAllList;

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
            filterNoteAllList = notes;

        });

    }

    private void loadData(int i) {
        if (i == 0) {
            notesViewModel.getAllNotes.observe(this, notes -> {
                setNotesAdapter(notes);
                filterNoteAllList = notes;

            });
        } else if (i == 1) {
            notesViewModel.highToLow.observe(this, notes -> {
                setNotesAdapter(notes);
                filterNoteAllList = notes;

            });
        } else if (i == 2) {
            notesViewModel.lowToHigh.observe(this, notes -> {
                setNotesAdapter(notes);
                filterNoteAllList = notes;

            });
        }
    }

    public void setNotesAdapter(List<Notes> notes) {
        notesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        notesAdapter = new NotesAdapter(MainActivity.this, notes);
        notesRecyclerView.setAdapter(notesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_notes_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.ic_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setQueryHint("Search Note Here...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                NoteFilter(newText);
                return true;
            }
        });

        return true;
    }

    private void NoteFilter(String newText) {
        ArrayList<Notes> filterName = new ArrayList<>();
        for (Notes notes : this.filterNoteAllList) {
            if (notes.notesTittle.contains(newText) || notes.notesSubTittle.contains(newText)) {
                filterName.add(notes);
            }
            this.notesAdapter.searchNotes(filterName);
        }
    }
}