package com.rtr.omdbfilms.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rtr.omdbfilms.base.BaseViewModel
import com.rtr.omdbfilms.datasource.MoviesDataSource
import com.rtr.omdbfilms.datasource.MoviesDataSourceFactory
import com.rtr.omdbfilms.model.MovieModel
import java.util.concurrent.Executor
import java.util.concurrent.Executors


/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * View model for home listing screen
 */
class HomeListingViewModel(app : Application) : BaseViewModel(app)  {

    var isDataEmpty : ObservableBoolean = ObservableBoolean(false)
    var moviesList: LiveData<PagedList<MovieModel>> = MutableLiveData()
    private var executor: Executor = Executors.newFixedThreadPool(5)
    private var dataSource: LiveData<MoviesDataSource> = MutableLiveData()
    private var factory: MoviesDataSourceFactory = MoviesDataSourceFactory("Marvel")

    private val pageConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(10)
        .setPageSize(10).build()

    init {
        dataSource = factory.moviesDataSourceLiveData
        moviesList = LivePagedListBuilder(factory, pageConfig)
            .setFetchExecutor(executor)
            .build()
    }

    /**
     * Method to get movie list based on search key
     */
    fun getMovieListBasedOnSearch(search : String? = null){
        factory.updateSearchQuery(search)
        factory.moviesDataSourceLiveData.value?.invalidate()
    }
}