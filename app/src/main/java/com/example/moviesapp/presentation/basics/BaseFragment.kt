package com.example.moviesapp.presentation.basics

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import dagger.android.support.DaggerFragment

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

abstract class BaseFragment : DaggerFragment() {

    protected fun showFragmentInCurrentManager(
        @IdRes containerId: Int, fragmentToShow: BaseFragment?,
        backSack: Boolean
    ) {
        showFragment(fragmentManager, containerId, fragmentToShow, backSack)
    }

    private fun showFragment(
        manager: FragmentManager?,
        @IdRes containerId: Int, fragmentToShow: BaseFragment?,
        backSack: Boolean
    ) {
        if (fragmentToShow != null && manager != null) {
            val ft = manager.beginTransaction()
                .replace(containerId, fragmentToShow, fragmentToShow::class.java.simpleName)
            if (backSack)
                ft.addToBackStack(fragmentToShow::class.java.simpleName)
            ft.commit()
        }
    }

    protected fun popBackStackTillEntry(entryIndex: Int) {
        val fragmentManager = fragmentManager
        if (fragmentManager != null) {
            if (fragmentManager.backStackEntryCount <= entryIndex) {
                return
            }
            val entry = fragmentManager.getBackStackEntryAt(entryIndex)
            fragmentManager.popBackStackImmediate(
                entry.id,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
    }
}