package com.ankur.mynotes.di

import androidx.lifecycle.ViewModel
import com.ankur.mynotes.ui.viewmodel.NotesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NotesViewModel::class)
    abstract fun bindMainViewModel(notesViewModel: NotesViewModel): ViewModel
}
