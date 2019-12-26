package com.example.stackapp.presentation.fragments.tags

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stackapp.R
import com.example.stackapp.databinding.FragmentTagsBinding
import com.example.stackapp.presentation.basics.BaseBindModelFragment
import com.example.stackapp.presentation.basics.createViewModel
import com.example.stackapp.presentation.fragments.tags.adapter.TagsPagedListAdapter
import kotlinx.android.synthetic.main.fragment_tags.*
import javax.inject.Inject

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

class TagsFragment : BaseBindModelFragment<FragmentTagsBinding, TagsVM>() {

    @Inject
    lateinit var tagsPagedListAdapter: TagsPagedListAdapter

    override fun getLayoutId() = R.layout.fragment_tags

    override fun initViewModel(): TagsVM {
        return createViewModel(viewModelFactory) {}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.startConfigureLoad()
            viewModel.listenConnectivity()
        }

        initAdapter()
        observe()
    }

    private fun initAdapter() {
        rvTags.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        rvTags.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvTags.adapter = tagsPagedListAdapter
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
        viewModel.tags?.observe(this@TagsFragment, Observer { tagsPagedListAdapter.submitList(it) })
        viewModel.tagsDataSourceFactory.sourceLiveData.observe(this@TagsFragment, Observer {
            viewModel.initialLoadState()?.observe(this, Observer { t -> viewModel.observeInitCommentsState(t) })
            viewModel.getInitLoadError()?.observe(this, Observer { t -> viewModel.observeInitError(t) })
            viewModel.getNextLoadError()?.observe(this, Observer { t -> viewModel.observeNextError(t) })
        })
    }
}