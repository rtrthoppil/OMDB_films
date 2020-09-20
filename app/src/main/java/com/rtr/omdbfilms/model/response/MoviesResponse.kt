package com.rtr.omdbfilms.model.response

import com.google.gson.annotations.SerializedName
import com.rtr.omdbfilms.base.BaseResponse

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Data response class for movie list
 */
data class MoviesResponse(
    @SerializedName("totalResults") val totalResults : String = "",
    @SerializedName("Response") val responseStatus : String = "",
    @SerializedName("Search") val searchList : MutableList<SearchResult>? = mutableListOf()
) : BaseResponse()

/**
 * Data class for movie for search list
 */
data class SearchResult(
    @SerializedName("imdbID") val movieId : String = "",
    @SerializedName("Title") val title : String = "",
    @SerializedName("Year") val year : String = "",
    @SerializedName("Type") val type : String = "",
    @SerializedName("Poster") val posterUrl : String = ""
)