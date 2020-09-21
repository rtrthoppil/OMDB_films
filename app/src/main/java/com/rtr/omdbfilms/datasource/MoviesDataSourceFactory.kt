package com.rtr.omdbfilms.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.rtr.omdbfilms.model.MovieModel

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Factory class for initializing data source
 */
class MoviesDataSourceFactory(private var searchKey : String? = null) : DataSource.Factory<Long, MovieModel>() {

    private lateinit var moviesDataSource : MoviesDataSource
    var moviesDataSourceLiveData : MutableLiveData<MoviesDataSource> = MutableLiveData()


    override fun create(): DataSource<Long, MovieModel> {
        moviesDataSource = MoviesDataSource(searchKey)
        moviesDataSourceLiveData.postValue(moviesDataSource)
        return moviesDataSource
    }

    /**
     * Method to change the search key
     */
    fun updateSearchQuery(newSearch : String? = null) {
        this.searchKey = newSearch
    }
}