package com.ankur.mynotes.persistence

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    //adding strategy for replacing old note with new note
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createNote(note: NoteModel)

    @Update
    fun updateNote(note: NoteModel)

    @Delete
    fun deleteNote(note: NoteModel)

    @Query("DELETE FROM table_note WHERE id = :id")
    fun deleteNoteById(id: Int)

    @Query("SELECT * FROM table_note")
    fun fetchAllNotes():LiveData<List<NoteModel>>
}