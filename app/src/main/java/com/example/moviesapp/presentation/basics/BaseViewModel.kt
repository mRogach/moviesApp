package com.example.moviesapp.presentation.basics

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.moviesapp.R
import com.example.moviesapp.data.api.ErrorsHandler
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

abstract class BaseViewModel(private val context: Context) : ViewModel() {

    val apiErrorHandler = SingleFireLiveData<String>()
    val progressHandler = SingleFireLiveData<Boolean>()

    private val disposable = CompositeDisposable()

    private fun convertError(apiError: Triple<ErrorsHandler.ApiError, String?, Int?>): Pair<String?, Int?> {
        val resources = context.resources
        return Pair(
            when (apiError.first) {
                ErrorsHandler.ApiError.CONNECTION -> resources?.getString(R.string.error_msg_connection)
                ErrorsHandler.ApiError.TIMEOUT -> resources?.getString(R.string.error_msg_timeout)
                ErrorsHandler.ApiError.UNKNOWN -> resources?.getString(R.string.error_msg_unknown)
                ErrorsHandler.ApiError.BACKEND, ErrorsHandler.ApiError.REQUEST -> apiError.second
            }, apiError.third
        )
    }

    protected fun notifyErrorMsg(error: Pair<String?, Int?>) = apiErrorHandler.postValue(error.first)
    protected fun notifyProgress(isProgress: Boolean) = progressHandler.postValue(isProgress)

    private fun <T> onSuccess(s: T, caller: (T) -> Unit) {
        caller.invoke(s)
    }

    private fun onSuccess(caller: () -> Unit) {
        caller.invoke()
    }

    private fun onError(t: Throwable, caller: ((Pair<String?, Int?>) -> Unit)?) {
        val error = convertError(ErrorsHandler.parseNetworkError(t))
        caller?.invoke(error)
    }

    fun <T> Observable<T>.execute(next: (T) -> Unit, error: (Pair<String?, Int?>) -> Unit): Disposable {
        val d = this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ onSuccess(it, next) }, { onError(it, error) })
        disposable.add(d)
        return d
    }

    fun <T> Flowable<T>.execute(next: (T) -> Unit, error: (Pair<String?, Int?>) -> Unit): Disposable {
        val d = this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ onSuccess(it, next) }, { onError(it, error) })
        disposable.add(d)
        return d
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

}