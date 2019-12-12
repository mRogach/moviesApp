package com.example.moviesapp.presentation.basics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

abstract class BaseBindModelFragment<T : ViewDataBinding, M : BaseViewModel> : BaseFragment() {

    protected lateinit var binding: T
    protected lateinit var viewModel: M

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!recreateViewModel()) prepareViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        performDataBinding()
        return binding.root
    }

    private fun performDataBinding() {
        if (recreateViewModel()) prepareViewModel()
        binding.lifecycleOwner = viewLifecycleOwner
//        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }

    private fun prepareViewModel() {
        viewModel = initViewModel().apply {
            observe(apiErrorHandler) {  }
            observe(progressHandler) {  }
        }
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun initViewModel(): M

    //If you wait date from live observer's
    open fun recreateViewModel() = true
}