package com.rtr.omdbfilms.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rtr.omdbfilms.databinding.LayoutMovieBinding
import com.rtr.omdbfilms.model.MovieModel
import com.rtr.omdbfilms.utils.OnClickMovieItem

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Paged list adapter class for listing movies
 */
class MovieListAdapter(var listener: OnClickMovieItem) : PagedListAdapter<MovieModel, MovieListAdapter.MovieViewHolder>(MovieModel.DIFF_CALL){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.setModel(getItem(position) ?: MovieModel())
    }

    /**
     * View holder class for movie listing
     */
    inner class MovieViewHolder(var binding: LayoutMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setModel(item: MovieModel) {
            item.clickListener = listener
            binding.model = item
        }
    }
}