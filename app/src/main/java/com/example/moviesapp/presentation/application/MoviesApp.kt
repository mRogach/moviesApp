package com.example.moviesapp.presentation.application

import com.example.moviesapp.presentation.application.di.DaggerAppComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */
class MoviesApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}