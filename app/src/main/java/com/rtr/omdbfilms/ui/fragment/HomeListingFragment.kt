package com.rtr.omdbfilms.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.rtr.omdbfilms.R
import com.rtr.omdbfilms.base.BaseFragment
import com.rtr.omdbfilms.databinding.FragmentHomeBinding
import com.rtr.omdbfilms.model.MovieModel
import com.rtr.omdbfilms.ui.adapter.MovieListAdapter
import com.rtr.omdbfilms.utils.ClickUtilsCallback
import com.rtr.omdbfilms.utils.OnClickMovieItem
import com.rtr.omdbfilms.viewmodel.HomeListingViewModel

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Fragment class for home listing screen
 */
class HomeListingFragment :  BaseFragment(), OnClickMovieItem {

    lateinit var viewModel: HomeListingViewModel
    lateinit var binding: FragmentHomeBinding
    lateinit var adapter: MovieListAdapter
    private lateinit var clickUtilsCallback: ClickUtilsCallback

    companion object {
        fun newInstance(): HomeListingFragment {
            return HomeListingFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeListingViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val parentView = super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        setContentViewForFragment(binding.root, viewModel)
        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        setAdapterForMovieListing()
        addObservers()
    }

    /**
     * Method to add observer
     */
    private fun addObservers(){
        viewModel.moviesList.observe(viewLifecycleOwner, Observer (adapter::submitList))
        binding.searchViewMovies.setOnQueryTextListener(searchListener)
    }

    /**
     * Method to attach listener for click
     */
    fun setOnClickAction(clickUtilsCallback: ClickUtilsCallback) {
        this.clickUtilsCallback = clickUtilsCallback
    }

    /**
     * Method to set adapter
     */
    private fun setAdapterForMovieListing(){
        adapter = MovieListAdapter(this)
        binding.recyclerViewMovies.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this.context,2)
            adapter = this@HomeListingFragment.adapter
        }
    }

    /**
     * Listener for search
     */
    private val searchListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            viewModel.getMovieListBasedOnSearch(query)
            return false
        }
        override fun onQueryTextChange(newText: String?): Boolean {
            return true
        }
    }

    override fun onClickItem(item: MovieModel) {
        clickUtilsCallback.onClickItem(item)
    }
}