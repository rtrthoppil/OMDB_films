package com.rtr.omdbfilms.model

import android.graphics.drawable.Drawable
import androidx.databinding.ObservableField

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */


/**
 * Data class for setting refresh view data
 */
data class RefreshModel(

    var title: ObservableField<String> = ObservableField(),
    var message: ObservableField<String> = ObservableField(),
    var positiveButtonTitle: ObservableField<String> = ObservableField(),
    var iconResourceId: ObservableField<Drawable> = ObservableField()
)