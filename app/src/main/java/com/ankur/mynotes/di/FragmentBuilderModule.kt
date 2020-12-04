package com.ankur.mynotes.di

import com.ankur.mynotes.ui.CreateNoteFragment
import com.ankur.mynotes.ui.EditNoteFragment
import com.ankur.mynotes.ui.MainActivity
import com.ankur.mynotes.ui.NotesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
// declare all the fragments here , dependency of fragments are provided by this module
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeNotesFragment(): NotesFragment

    @ContributesAndroidInjector
    abstract fun contributeCreateFragment(): CreateNoteFragment

    @ContributesAndroidInjector
    abstract fun contributeEditFragment(): EditNoteFragment

}