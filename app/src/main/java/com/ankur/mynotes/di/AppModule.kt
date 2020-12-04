package com.ankur.mynotes.di

import android.app.Application
import androidx.room.Room
import com.ankur.mynotes.persistence.NoteDB
import com.ankur.mynotes.persistence.NoteDao
import com.ankur.mynotes.repository.NoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

//Provide all the app level dependency here
@Module
class AppModule {


    @Singleton
    @Provides
    fun providesAppDatabase(app: Application):NoteDB{
        return Room.databaseBuilder(app,NoteDB::class.java,"note_database").build()
    }

    @Singleton
    @Provides
    fun providesNoteDao(db: NoteDB): NoteDao {
        return db.noteDao()
    }

    @Provides
    fun providesRepository(noteDao: NoteDao):NoteRepository{
        return NoteRepository(noteDao)
    }
}