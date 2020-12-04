package com.ankur.mynotes.di

import com.ankur.mynotes.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

// declare all the activity here , dependency of activity are provided by this module

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [ViewModelModule::class,FragmentBuilderModule::class])
    abstract fun contributeMainActivity(): MainActivity

}