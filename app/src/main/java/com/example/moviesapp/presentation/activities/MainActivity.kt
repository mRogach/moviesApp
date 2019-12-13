package com.example.moviesapp.presentation.activities

import android.os.Bundle
import com.example.moviesapp.R
import com.example.moviesapp.presentation.basics.BaseActivity
import com.example.moviesapp.presentation.fragments.movies.MoviesFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.view.*

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

class MainActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbarLayout.toolbar).apply { title = "Popular films" }

        if (savedInstanceState == null) {
            replaceFragment(R.id.fragmentContainer, MoviesFragment(), false)
        }
    }
}
