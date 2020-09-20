package com.rtr.omdbfilms.model

import androidx.databinding.ObservableField
import java.util.*

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Data class for movie details
 */
data class MovieDetailsModel(
    val movieId : ObservableField<String> = ObservableField(),
    val title : ObservableField<String> = ObservableField(),
    val year : ObservableField<String> = ObservableField(),
    val releaseDate : ObservableField<String> = ObservableField(),
    val duration : ObservableField<String> = ObservableField(),
    val genre : ObservableField<String> = ObservableField(),
    val director : ObservableField<String> = ObservableField(),
    val writer : ObservableField<String> = ObservableField(),
    val actors : ObservableField<String> = ObservableField(),
    val language : ObservableField<String> = ObservableField(),
    val country : ObservableField<String> = ObservableField(),
    val award : ObservableField<String> = ObservableField(),
    val posterUrl : ObservableField<String> = ObservableField(),
    val rating : ObservableField<String> = ObservableField(),
    val voting : ObservableField<String> = ObservableField(),
    val production :ObservableField<String> = ObservableField()
)