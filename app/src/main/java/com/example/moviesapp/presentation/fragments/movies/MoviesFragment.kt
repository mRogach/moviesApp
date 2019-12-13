package com.example.moviesapp.presentation.fragments.movies

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentMoviesBinding
import com.example.moviesapp.presentation.basics.BaseBindModelFragment
import com.example.moviesapp.presentation.basics.createViewModel
import com.example.moviesapp.presentation.fragments.movies.adapter.MoviesPagedListAdapter
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

class MoviesFragment : BaseBindModelFragment<FragmentMoviesBinding, MoviesVM>() {

    @Inject
    lateinit var moviesPagedListAdapter: MoviesPagedListAdapter

    override fun getLayoutId() = R.layout.fragment_movies

    override fun initViewModel(): MoviesVM {
        return createViewModel(viewModelFactory) {
            startConfigureLoad()
            listenConnectivity()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        observe()
    }

    private fun initAdapter() {
        rvMovies.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        rvMovies.layoutManager = GridLayoutManager(context, 3)
        rvMovies.adapter = moviesPagedListAdapter
        srlListRefresh.apply {
            setColorSchemeColors(ResourcesCompat.getColor(resources, R.color.colorPrimary, null))
            setOnRefreshListener {
                if (isRefreshing) {
                    isRefreshing = false
                }
                viewModel.retry()
            }
        }
    }

    private fun observe() {
        viewModel.movies?.observe(this@MoviesFragment, Observer { moviesPagedListAdapter.submitList(it) })
        viewModel.moviesDataSourceFactory.sourceLiveData.observe(this@MoviesFragment, Observer {
            viewModel.initialLoadState()?.observe(this, Observer { t -> viewModel.observeInitCommentsState(t) })
            viewModel.getInitLoadError()?.observe(this, Observer { t -> viewModel.observeInitError(t) })
            viewModel.getNextLoadError()?.observe(this, Observer { t -> viewModel.observeNextError(t) })
        })
    }
}