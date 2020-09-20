package com.rtr.omdbfilms.viewmodel

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rtr.omdbfilms.R
import com.rtr.omdbfilms.base.BaseViewModel
import com.rtr.omdbfilms.datasource.MoviesDataSource
import com.rtr.omdbfilms.datasource.MoviesDataSourceFactory
import com.rtr.omdbfilms.model.MovieModel
import com.rtr.omdbfilms.utils.AppConstUtils
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
    var searchKey : String = AppConstUtils.API_KEY_SEARCH_VALUE
    private var factory: MoviesDataSourceFactory = MoviesDataSourceFactory(searchKey)
    var response : MediatorLiveData<String> = MediatorLiveData()


    private val pageConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(10)
        .setPageSize(10).build()

    init {
        dataSource = factory.moviesDataSourceLiveData
        moviesList = LivePagedListBuilder(factory, pageConfig)
            .setFetchExecutor(executor)
            .build()
        addListerForError()
    }

    /**
     * Method to get movie list based on search key
     */
    fun getMovieListBasedOnSearch(search : String? = null){
        searchKey = search ?: AppConstUtils.API_KEY_SEARCH_VALUE
        factory.updateSearchQuery(search)
        factory.moviesDataSourceLiveData.value?.invalidate()
    }

    /**
     * Method to add listeners for error
     */
    private fun addListerForError(){
        response.addSource(factory.moviesDataSourceLiveData, Observer {
            response.addSource(it.isInitialLoadingError, Observer { loadingError ->
                if(loadingError) showErrorMessageView(true)
                else showContentView(true)
            })
            response.addSource(it.isLoadingError, Observer { loadingError ->
                if(loadingError)
                    Toast.makeText(appContext, appContext.getString(R.string.error_message), Toast.LENGTH_SHORT).show()
            })
        })
    }

    override fun onClickRetryButton(view: View) {
        super.onClickRetryButton(view)
        getMovieListBasedOnSearch(searchKey)
    }
}