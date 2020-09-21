package com.rtr.omdbfilms.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rtr.omdbfilms.R
import com.rtr.omdbfilms.base.BaseFragment
import com.rtr.omdbfilms.databinding.FragmentMovieDetailsBinding
import com.rtr.omdbfilms.utils.AppConstUtils
import com.rtr.omdbfilms.viewmodel.MovieDetailsViewModel

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Fragment class for movie details screen
 */
class MovieDetailsFragment :  BaseFragment() {

    lateinit var viewModel: MovieDetailsViewModel
    lateinit var binding: FragmentMovieDetailsBinding

    companion object {
        fun newInstance(movieId : String): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val bundle = Bundle()
            bundle.putString(AppConstUtils.INTENT_KEY_MOVIE_ID, movieId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        addObservers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val parentView = super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        setContentViewForFragment(binding.root, viewModel)
        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        viewModel.getDataFromServer(arguments?.getString(AppConstUtils.INTENT_KEY_MOVIE_ID) ?: "")
    }

    /**
     * Method to add observers
     */
    private fun addObservers(){
        viewModel.response.observe(this, Observer {  })
    }
}