package com.rtr.omdbfilms.repository

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.rtr.omdbfilms.model.MovieDetailsModel
import com.rtr.omdbfilms.model.response.MovieDetailsResponse
import com.rtr.omdbfilms.utils.AppConstUtils
import com.rtr.omdbfilms.utils.MoviesApiService
import com.rtr.omdbfilms.utils.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Repository class for movie details API
 */
class MovieDetailsRepository {

    private var movieApiService : MoviesApiService? = null

    init {
        movieApiService = RetrofitHelper.getRetrofitInstance().create(MoviesApiService::class.java)
    }

    /**
     * Method to call API to server for movie details
     */
    fun getMovieDetails(movieId : String) : MutableLiveData<MovieDetailsModel>{
        val movieDetails = MutableLiveData<MovieDetailsModel>()
        val apiCall = movieApiService?.getMovieDetails(movieId = movieId)
        apiCall?.enqueue(object  : Callback<MovieDetailsResponse?> {
            override fun onFailure(call: Call<MovieDetailsResponse?>, error: Throwable) {
                movieDetails.postValue(null)
            }
            override fun onResponse(call: Call<MovieDetailsResponse?>, response: Response<MovieDetailsResponse?>) {
                if(response.isSuccessful && response.code() == AppConstUtils.API_SUCCESS)
                    movieDetails.postValue(getMovieDetails(response.body()))
                else movieDetails.postValue(null)
            }
        })
        return movieDetails
    }

    /**
     * Method to arrange movie details
     */
    private fun getMovieDetails(response : MovieDetailsResponse?) : MovieDetailsModel?{
        response?.let {
            return MovieDetailsModel(
                movieId = ObservableField(it.imdbID ?: ""),
                title = ObservableField(it.title ?: ""),
                year = ObservableField(it.year?.toString() ?: ""),
                releaseDate = ObservableField(it.releasedDate ?: ""),
                duration = ObservableField(it.duration ?: ""),
                genre = ObservableField(it.genre ?: ""),
                director = ObservableField(it.director ?: ""),
                writer = ObservableField(it.writer ?: ""),
                actors = ObservableField(it.actors ?: ""),
                language = ObservableField(it.language ?: ""),
                country = ObservableField(it.country ?: ""),
                award = ObservableField(it.awards ?: ""),
                posterUrl = ObservableField(it.posterUrl ?: ""),
                rating = ObservableField(it.imdbRating?.toString() ?: ""),
                voting = ObservableField(it.imdbVotes ?: ""),
                production = ObservableField(it.production ?: "")
            )
        }
        return null
    }
}