package com.rtr.omdbfilms.model

import android.graphics.Movie
import android.os.Parcelable
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.rtr.omdbfilms.utils.OnClickMovieItem
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize


/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Data class for movie listing
 */
@Parcelize
data class MovieModel(
    var movieId : String? = "",
    var title : String? = "",
    var year : String? = "",
    var type : String? = "",
    var posterUrl : String? = "") : Parcelable{

    @IgnoredOnParcel
    var clickListener : OnClickMovieItem? = null

    fun onClickMovieItem(view : View){
        clickListener?.onClickItem(this)
    }

    companion object{
        /**
         * For checking objects during pagination
         */
        val DIFF_CALL: DiffUtil.ItemCallback<MovieModel> = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem.movieId === newItem.movieId
            }

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem.movieId == newItem.movieId
            }
        }
    }
}