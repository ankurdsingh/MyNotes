package com.ankur.mynotes.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteModel::class],version=1)
abstract class NoteDB: RoomDatabase() {

    abstract fun noteDao(): NoteDao

}