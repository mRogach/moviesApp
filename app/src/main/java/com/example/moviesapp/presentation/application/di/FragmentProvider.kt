package com.example.moviesapp.presentation.application.di

import com.example.moviesapp.presentation.basics.BaseFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

@Module
abstract class FragmentProvider {

    @ContributesAndroidInjector()
    abstract fun bindBaseFragment(): BaseFragment
}