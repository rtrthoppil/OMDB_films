package com.rtr.omdbfilms.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.rtr.omdbfilms.model.MovieModel
import com.rtr.omdbfilms.model.response.MoviesResponse
import com.rtr.omdbfilms.utils.AppConstUtils.API_SUCCESS
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
 * Data source class for movie listing
 */
class MoviesDataSource(private val searchKey : String? = null) : PageKeyedDataSource<Long, MovieModel>() {

    private var movieApiService : MoviesApiService? = null
    var isInitialLoadingError : MutableLiveData<Boolean> = MutableLiveData(false)
    var isLoadingError : MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        movieApiService = RetrofitHelper.getRetrofitInstance().create(MoviesApiService::class.java)
    }

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, MovieModel>
    ) {
        val apiCall = movieApiService?.getMovieList(searchKey = searchKey)
        apiCall?.enqueue(object  : Callback<MoviesResponse?> {
            override fun onFailure(call: Call<MoviesResponse?>, error: Throwable) {
                isInitialLoadingError.postValue(true)
            }
            override fun onResponse(call: Call<MoviesResponse?>, response: Response<MoviesResponse?>) {
                if(response.isSuccessful && response.code() == API_SUCCESS) {
                    val list = getMovieList(response.body())
                    callback.onResult(list, null, 2.toLong())
                }
                else isInitialLoadingError.postValue(true)
            }
        })
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, MovieModel>) {
        val apiCall = movieApiService?.getMovieList(searchKey = searchKey, pageNumber = params.key.toString())
        apiCall?.enqueue(object  : Callback<MoviesResponse?> {
            override fun onFailure(call: Call<MoviesResponse?>, error: Throwable) {
                isLoadingError.postValue(true)
            }
            override fun onResponse(call: Call<MoviesResponse?>, response: Response<MoviesResponse?>) {
                if(response.isSuccessful && response.code() == API_SUCCESS) {
                    val list = getMovieList(response.body())
                    callback.onResult(list, params.key + 1)
                }
                else isLoadingError.postValue(true)
            }
        })
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, MovieModel>) {
        /**/
    }

    /**
     * Method to get arranged movie list from response
     */
    private fun getMovieList(response : MoviesResponse?) : MutableList<MovieModel>{
        val list = mutableListOf<MovieModel>()
        response?.let {
            val searchList = it.searchList
            searchList?.let { searchList ->
                for(item in searchList) list.add(
                    MovieModel(movieId = item.movieId,
                    title = item.title,
                    type = item.type,
                    year = item.year,
                    posterUrl = item.posterUrl)
                )
            }
        }
        return list
    }
}