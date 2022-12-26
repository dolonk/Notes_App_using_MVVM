package com.example.notes.Services.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes_Database")
public class Notes {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "Notes_tittle")
    public String notesTittle;

    @ColumnInfo(name = "Notes_subTittle")
    public String notesSubTittle;

    @ColumnInfo(name = "Notes_date")
    public String notesDate;

    @ColumnInfo(name = "Notes")
    public String notes;

    @ColumnInfo(name = "Notes_priority")
    public String NotesPriority;
}
