package com.rtr.omdbfilms.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.rtr.omdbfilms.R
import com.rtr.omdbfilms.base.BaseActivity
import com.rtr.omdbfilms.databinding.ActivityHomeBinding
import com.rtr.omdbfilms.model.MovieModel
import com.rtr.omdbfilms.ui.fragment.HomeListingFragment
import com.rtr.omdbfilms.ui.fragment.MovieDetailsFragment
import com.rtr.omdbfilms.utils.ClickUtilsCallback
import com.rtr.omdbfilms.viewmodel.HomeScreenViewModel

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Activity class for Home screen
 */
class HomeActivity : BaseActivity(), ClickUtilsCallback {

    private lateinit var viewModel: HomeScreenViewModel
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeScreenViewModel::class.java)
        binding = setContentViewForActivity(R.layout.activity_home, viewModel) as ActivityHomeBinding
        binding.viewModel = viewModel
        viewModel.setUpHeaderForHome()
        launchHomeListing()
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is HomeListingFragment) {
            fragment.setOnClickAction(this)
        }
    }

    /**
     * Method to load home listing screen
     */
    private fun launchHomeListing(){
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.beginTransaction().replace(binding.layoutHomeScreen.id,
            HomeListingFragment.newInstance(), HomeListingFragment::class.java.toString() ).commit()
    }

    /**
     * Method to attach details fragment
     */
    private fun launchMovieDetailsScreen(movieId : String){
        supportFragmentManager.beginTransaction().add(binding.layoutHomeScreen.id,
            MovieDetailsFragment.newInstance(movieId), MovieDetailsFragment::class.java.toString() )
            .addToBackStack(MovieDetailsFragment::class.java.toString()).commit()
    }

    override fun onClickItem(item: MovieModel) {
        launchMovieDetailsScreen(item?.movieId ?: "")
    }
}