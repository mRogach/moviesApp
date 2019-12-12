package com.example.moviesapp.presentation.fragments.movies

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentMoviesBinding
import com.example.moviesapp.presentation.basics.BaseBindModelFragment
import com.example.moviesapp.presentation.basics.prepareViewModel
import kotlinx.android.synthetic.main.fragment_movies.*

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

class MoviesFragment : BaseBindModelFragment<FragmentMoviesBinding, MoviesVM>() {

    companion object {
        private const val EXTRA_SELECTED_TAB = "EXTRA_SELECTED_TAB"
        fun of(tab: Int) = MoviesFragment().also { it.arguments = bundleOf(EXTRA_SELECTED_TAB to tab) }
    }

    override fun getLayoutId() = R.layout.fragment_movies

    override fun initViewModel(): MoviesVM {
        return prepareViewModel {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMovies.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        srlListRefresh.apply {
            setColorSchemeColors(ResourcesCompat.getColor(resources, R.color.colorPrimary, null))
            setOnRefreshListener { viewModel.fetchDoctors() }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchDoctors()
    }
}