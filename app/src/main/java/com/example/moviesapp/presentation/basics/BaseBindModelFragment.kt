package com.example.moviesapp.presentation.basics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.BR
import javax.inject.Inject

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

abstract class BaseBindModelFragment<T : ViewDataBinding, M : BaseViewModel> : BaseFragment() {

    protected lateinit var binding: T
    protected lateinit var viewModel: M

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun initViewModel(): M

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = initViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        performDataBinding()
        return binding.root
    }

    private fun performDataBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }

}