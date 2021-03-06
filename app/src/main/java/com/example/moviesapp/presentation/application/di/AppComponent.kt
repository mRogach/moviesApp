package com.example.moviesapp.presentation.application.di

import android.app.Application
import com.example.moviesapp.presentation.application.MoviesApp
import com.example.moviesapp.presentation.application.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

@Singleton
@Component(modules = [(AppModule::class), (AndroidSupportInjectionModule::class), (ActivityProvider::class), (FragmentProvider::class), (ViewModelModule::class)])
interface AppComponent : AndroidInjector<MoviesApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}