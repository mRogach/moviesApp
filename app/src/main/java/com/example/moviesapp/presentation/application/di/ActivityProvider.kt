package com.example.moviesapp.presentation.application.di

import com.example.moviesapp.presentation.activities.MainActivity
import com.example.moviesapp.presentation.basics.BaseActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

@Module
abstract class ActivityProvider {

    @ContributesAndroidInjector()
    abstract fun bindBaseActivity(): BaseActivity

    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity
}