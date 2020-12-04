package com.ankur.mynotes.repository

import androidx.lifecycle.LiveData
import com.ankur.mynotes.persistence.NoteDao
import com.ankur.mynotes.persistence.NoteModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteRepository @Inject constructor(val noteDao: NoteDao) {

    fun insert(note: NoteModel){
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.createNote(note)
        }
    }
    fun delete(note: NoteModel){
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.deleteNote(note)
        }
    }fun deleteNoteById(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.deleteNoteById(id)
        }
    }
    fun update(note: NoteModel){
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.updateNote(note)
        }
    }
    fun getAllNotes():LiveData<List<NoteModel>>{
           return noteDao.fetchAllNotes()
    }
}