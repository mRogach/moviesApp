package com.example.moviesapp.presentation.application.di

import com.example.moviesapp.presentation.fragments.movies.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

@Module
abstract class FragmentProvider {

    @ContributesAndroidInjector()
    abstract fun bindMoviesFragment(): MoviesFragment
}