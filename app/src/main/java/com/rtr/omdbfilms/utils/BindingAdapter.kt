package com.rtr.omdbfilms.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rtr.omdbfilms.R

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */



/**
 * File for declaring custom binding methods
 */

/**
 * Image binding
 */
@BindingAdapter("loadImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl).apply(RequestOptions())
        .placeholder(R.drawable.ic_account_box_black_48dp)
        //.diskCacheStrategy(DiskCacheStrategy.NONE)
        //.skipMemoryCache(true)
        .into(view)
}