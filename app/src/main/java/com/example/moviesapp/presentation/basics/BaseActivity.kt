package com.example.moviesapp.presentation.basics

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.moviesapp.R
import dagger.android.support.DaggerAppCompatActivity


/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

abstract class BaseActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (fragment != null && fragment is BaseFragment) {
            super.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    protected fun replaceFragment(
        @IdRes containerId: Int, fragmentToShow: Fragment?,
        backSack: Boolean
    ) {
        if (fragmentToShow != null) {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            val ft = supportFragmentManager
                .beginTransaction()
                .replace(containerId, fragmentToShow, fragmentToShow::class.java.simpleName)
            if (backSack)
                ft.addToBackStack(fragmentToShow::class.java.simpleName)
            ft.commitAllowingStateLoss()
        }
    }

    @LayoutRes
    abstract fun getLayoutId(): Int
}