package com.rtr.omdbfilms.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.rtr.omdbfilms.base.BaseViewModel
import com.rtr.omdbfilms.model.MovieDetailsModel
import com.rtr.omdbfilms.repository.MovieDetailsRepository

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * View model for movie detail screen
 */
class MovieDetailsViewModel (app : Application) : BaseViewModel(app)  {

    var movieDetailsLiveData : MutableLiveData<MovieDetailsModel> = MutableLiveData()
    var movieDetails : ObservableField<MovieDetailsModel> = ObservableField()

    var repository: MovieDetailsRepository = MovieDetailsRepository()

    /**
     * Method to get movie details from server
     */
    fun getMovieDetails(movieId : String) : MutableLiveData<MovieDetailsModel>{
        movieDetailsLiveData = repository.getMovieDetails(movieId)
        return movieDetailsLiveData
    }
}