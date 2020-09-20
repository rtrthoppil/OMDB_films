package com.rtr.omdbfilms.viewmodel

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.rtr.omdbfilms.R
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
class MovieDetailsViewModel (app : Application) : BaseViewModel(app) {

    var response : MediatorLiveData<String> = MediatorLiveData()
    var movieDetails : ObservableField<MovieDetailsModel> = ObservableField()
    var repository: MovieDetailsRepository = MovieDetailsRepository()
    var movieId : String? = null

    /**
     * Method to get movie details from server
     */
   fun getDataFromServer(movieId : String?){
       this.movieId = movieId
        if(checkInternetConnectivity()) {
            showProgressView(true)
            response.addSource(repository.getMovieDetails(movieId ?: ""), Observer {
                if (it?.movieId?.get().isNullOrEmpty())
                    showProgressView(true)
                else movieDetails.set(it)
                showProgressView(false)
            })
        }
   }

    override fun onClickRetryButton(view: View) {
        super.onClickRetryButton(view)
        getDataFromServer(movieId ?: "")
    }
}