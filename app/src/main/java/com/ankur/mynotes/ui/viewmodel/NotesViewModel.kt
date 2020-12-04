package com.ankur.mynotes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ankur.mynotes.persistence.NoteModel
import com.ankur.mynotes.repository.NoteRepository
import javax.inject.Inject

class NotesViewModel @Inject constructor(val noteRepository: NoteRepository): ViewModel(){

    fun saveNote(note: NoteModel){
        noteRepository.insert(note)
    }

    fun getAllNotes():LiveData<List<NoteModel>>{
        return noteRepository.getAllNotes()
    }

    fun updateNote(note: NoteModel){
        noteRepository.update(note)
    }

    fun deleteNote(note: NoteModel){
        noteRepository.delete(note)
    }

    fun deleteNoteById(id: Int){
        noteRepository.deleteNoteById(id)
    }
}