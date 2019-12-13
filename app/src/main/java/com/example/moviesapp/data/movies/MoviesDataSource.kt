package com.example.moviesapp.data.movies

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.moviesapp.data.api.ErrorsHandler
import com.example.moviesapp.data.api.MoviesAppRestClient
import com.example.moviesapp.data.models.Movie
import com.example.moviesapp.data.models.ResultResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

class MoviesDataSource constructor(
    private val moviesAppRestClient: MoviesAppRestClient
) :
    PageKeyedDataSource<Int, Movie>() {

    var initialLoadStateLiveData = MutableLiveData<ResultResponse.Status>()
    var initialLoadErrorLiveData = MutableLiveData<Triple<ErrorsHandler.ApiError, String?, Int?>>()
    var nextLoadErrorLiveData = MutableLiveData<Triple<ErrorsHandler.ApiError, String?, Int?>>()
    var paginatedNetworkStateLiveData = MutableLiveData<ResultResponse.Status>()
    private val compositeDisposable = CompositeDisposable()
    private var page: Int = 1

    private fun onMoviesFetched(movies: List<Movie>, callback: LoadInitialCallback<Int, Movie>) {
        initialLoadStateLiveData.postValue(ResultResponse.Status.SUCCESS)
        page = page.inc()
        callback.onResult(movies, page, page.dec())
    }

    private fun onNextMoviesFetched(movies: List<Movie>, callback: LoadCallback<Int, Movie>) {
        paginatedNetworkStateLiveData.postValue(ResultResponse.Status.SUCCESS)
        page = page.inc()
        callback.onResult(movies, page)
    }

    override fun invalidate() {
        super.invalidate()
        compositeDisposable.dispose()
        page = 1
    }

    fun clear() {
        compositeDisposable.clear()
        page = 1
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        initialLoadStateLiveData.postValue(ResultResponse.Status.LOADING)

        val loadFirstMovies = getMovies()
                .subscribe {
                    when (it.status) {
                        ResultResponse.Status.ERROR -> {
                            initialLoadErrorLiveData.postValue(it.error?.let { it1 ->
                                ErrorsHandler.parseNetworkError(
                                    it1
                                )
                            })
                            initialLoadStateLiveData.postValue(ResultResponse.Status.ERROR)
                        }
                        ResultResponse.Status.SUCCESS -> {
                            it.data?.let { it1 -> onMoviesFetched(it1, callback) }
                            initialLoadStateLiveData.postValue(ResultResponse.Status.SUCCESS)
                        }
                    }
                }
        compositeDisposable.add(loadFirstMovies)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        paginatedNetworkStateLiveData.postValue(ResultResponse.Status.LOADING)

        val loadNextMovies = getMovies()
                .subscribe {
                    when (it.status) {
                        ResultResponse.Status.ERROR -> nextLoadErrorLiveData.postValue(it.error?.let { it1 ->
                            ErrorsHandler.parseNetworkError(
                                it1
                            )
                        })
                        ResultResponse.Status.SUCCESS -> {
                            it.data?.let { it1 -> onNextMoviesFetched(it1, callback) }
                            paginatedNetworkStateLiveData.postValue(ResultResponse.Status.SUCCESS)
                        }
                    }
                }
        compositeDisposable.add(loadNextMovies)
    }

    private fun getMovies(): Observable<ResultResponse<List<Movie>>> {
        return moviesAppRestClient.moviesService.getPopularMovies("a92820dc0df3931554696bc0bbe3cb1d", page)
            .map { t -> ResultResponse.success(t.results) }
            .onErrorReturn { t -> ResultResponse.error(t) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {}
}