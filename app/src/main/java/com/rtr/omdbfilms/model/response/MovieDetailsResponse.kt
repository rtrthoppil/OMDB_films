package com.rtr.omdbfilms.model.response

import com.google.gson.annotations.SerializedName

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Data response class for movie details
 */
class MovieDetailsResponse(
    @SerializedName("Title") val title : String?,
    @SerializedName("Year") val year : Int?,
    @SerializedName("Rated") val rated : String?,
    @SerializedName("Released") val releasedDate : String?,
    @SerializedName("Runtime") val duration : String?,
    @SerializedName("Genre") val genre : String?,
    @SerializedName("Director") val director : String?,
    @SerializedName("Writer") val writer : String?,
    @SerializedName("Actors") val actors : String?,
    @SerializedName("Plot") val plot : String?,
    @SerializedName("Language") val language : String?,
    @SerializedName("Country") val country : String?,
    @SerializedName("Awards") val awards : String?,
    @SerializedName("Poster") val posterUrl : String?,
    @SerializedName("Ratings") val ratings : List<Ratings>?,
    @SerializedName("Metascore") val metaScore : Int?,
    @SerializedName("imdbRating") val imdbRating : Double?,
    @SerializedName("imdbVotes") val imdbVotes : String?,
    @SerializedName("imdbID") val imdbID : String?,
    @SerializedName("Type") val type : String?,
    @SerializedName("DVD") val dVD : String?,
    @SerializedName("BoxOffice") val boxOffice : String?,
    @SerializedName("Production") val production : String?,
    @SerializedName("Website") val website : String?,
    @SerializedName("Response") val response : Boolean?
)

data class Ratings (
    @SerializedName("Source") val source : String?,
    @SerializedName("Value") val value : String?
)