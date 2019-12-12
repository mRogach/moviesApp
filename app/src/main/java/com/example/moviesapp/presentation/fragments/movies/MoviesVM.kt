package com.example.moviesapp.presentation.fragments.movies

import android.content.Context
import android.util.SparseBooleanArray
import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.presentation.basics.BaseViewModel

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

class MoviesVM(private val context: Context) : BaseViewModel(context) {

    //Rest APi
//    private val doctorsService = DataProvider.restApi().professionalsService()

    private var isLoading = SparseBooleanArray()
    val showLoader = MutableLiveData<Boolean>()
    val listIsEmpty = MutableLiveData<Boolean>()

    fun fetchDoctors() {
        loadDoctorsList()
    }

    private fun loadDoctorsList() {
    }
}