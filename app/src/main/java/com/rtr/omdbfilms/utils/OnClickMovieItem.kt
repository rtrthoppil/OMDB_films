package com.rtr.omdbfilms.utils

import com.rtr.omdbfilms.model.MovieModel

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Click listener for movie item
 */
interface OnClickMovieItem {
    fun onClickItem(item : MovieModel)
}