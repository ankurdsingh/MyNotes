package com.ankur.mynotes.di

import android.app.Application
import com.ankur.mynotes.MyNotesApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuilderModule::class
    ]
)
interface AppComponent : AndroidInjector<MyNotesApplication> {

    //We have to instruct the component how to build itself. We do this with the @Component.Builder annotation.

    @Component.Builder
    interface Builder {

        // Method #1
        fun build(): AppComponent

        // Method #2
        @BindsInstance
        fun application(application: Application): Builder
    }
}