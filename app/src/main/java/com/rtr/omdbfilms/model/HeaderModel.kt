package com.rtr.omdbfilms.model

import android.graphics.drawable.Drawable
import androidx.databinding.ObservableField

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */


/**
 * Data class for header view
 */
data class HeaderModel(
    var showLeftIcon: ObservableField<Boolean> = ObservableField(false),
    var title: ObservableField<String> = ObservableField(),
    var leftIcon: ObservableField<Drawable> = ObservableField()
)