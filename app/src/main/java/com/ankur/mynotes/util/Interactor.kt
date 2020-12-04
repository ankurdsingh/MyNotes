package com.ankur.mynotes.util

import com.ankur.mynotes.persistence.NoteModel

interface Interactor {
    fun onItemSelected(position : Int, note: NoteModel?)
}