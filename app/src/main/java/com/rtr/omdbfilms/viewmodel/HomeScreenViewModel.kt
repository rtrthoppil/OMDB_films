package com.rtr.omdbfilms.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.rtr.omdbfilms.R
import com.rtr.omdbfilms.base.BaseViewModel
import com.rtr.omdbfilms.model.HeaderModel

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */


/**
 * View model for home screen
 */
class HomeScreenViewModel(app : Application) : BaseViewModel(app) {

    fun setUpHeaderForHome(){
        addHeaderData(HeaderModel(title = ObservableField(appContext.getString(R.string.app_name))))
    }
}
