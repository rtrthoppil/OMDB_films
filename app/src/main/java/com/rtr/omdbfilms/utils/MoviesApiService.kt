package com.rtr.omdbfilms.utils

import com.rtr.omdbfilms.model.response.MoviesResponse
import com.rtr.omdbfilms.utils.AppConstUtils.API_KEY
import com.rtr.omdbfilms.utils.AppConstUtils.API_KEY_PAGE
import com.rtr.omdbfilms.utils.AppConstUtils.API_KEY_SEARCH
import com.rtr.omdbfilms.utils.AppConstUtils.API_KEY_TYPE
import com.rtr.omdbfilms.utils.AppConstUtils.API_KEY_VALUE
import com.rtr.omdbfilms.utils.AppConstUtils.SEARCH_KEY
import com.rtr.omdbfilms.utils.AppConstUtils.SEARCH_PAGE
import com.rtr.omdbfilms.utils.AppConstUtils.SEARCH_TYPE
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * API interface for movies related APIs
 */
interface MoviesApiService {

    @GET("/")
    fun getMovieList(@Query(API_KEY) apiKey : String = API_KEY_VALUE,
                     @Query(API_KEY_SEARCH) searchKey : String? = SEARCH_KEY,
                     @Query(API_KEY_TYPE) type : String = SEARCH_TYPE,
                     @Query(API_KEY_PAGE) pageNumber : String = SEARCH_PAGE
    ) : Call<MoviesResponse?>

}