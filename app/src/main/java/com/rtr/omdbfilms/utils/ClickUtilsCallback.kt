package com.rtr.omdbfilms.utils

import com.rtr.omdbfilms.model.MovieModel

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Interface for click actions
 */
interface ClickUtilsCallback {

    fun onClickItem(item : MovieModel)

}