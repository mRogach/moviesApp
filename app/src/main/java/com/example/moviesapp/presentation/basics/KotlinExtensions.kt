package com.example.moviesapp.presentation.basics

import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.BaseRequestOptions


/**
 * Created by
 * Mykhailo on 12/12/2019.
 */


inline fun <reified T : ViewModel> Fragment.prepareViewModel(body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(this)[T::class.java]
    vm.body()
    return vm
}

inline fun <reified T : ViewModel> Fragment.prepareViewModel(
    factory: ViewModelProvider.Factory?,
    body: T.() -> Unit
): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L?, body: (T) -> Unit) =
    liveData?.observe(this, Observer(body))

fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}

fun ImageView.loadUrl(url: String?, request: BaseRequestOptions<*>) {
    Glide.with(context)
        .load(url)
        .apply(request)
        .into(this)
}