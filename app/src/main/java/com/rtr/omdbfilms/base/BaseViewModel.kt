package com.rtr.omdbfilms.base

import android.app.Application
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.rtr.omdbfilms.R
import com.rtr.omdbfilms.model.HeaderModel
import com.rtr.omdbfilms.model.RefreshModel
import com.rtr.omdbfilms.utils.NetworkUtils

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */



/**
 * Base ViewModel class for implementing common features of ViewModel classes
 */
open class BaseViewModel(var appContext: Application) : AndroidViewModel(appContext) {

    var showContent: ObservableBoolean = ObservableBoolean(true)
    var showProgress: ObservableBoolean = ObservableBoolean(false)
    var showHeader: ObservableBoolean = ObservableBoolean(false)
    var showErrorMessage: ObservableBoolean = ObservableBoolean(false)
    var refreshModel: ObservableField<RefreshModel> = ObservableField()
    var headerModel: ObservableField<HeaderModel> = ObservableField()

    init {
        refreshModel.set(getDefaultErrorMessageDetails())
    }

    /**
     * Method to display header
     */
    fun displayHeader(status: Boolean = true) {
        showHeader.set(status)
    }

    /**
     * Method to add header data
     */
    fun addHeaderData(data : HeaderModel?){
        data?.let { headerModel.set(it) }
        displayHeader(true)
    }

    /**
     * Method to show content view
     * @param status : Boolean value for show and hide
     */
    fun showContentView(status: Boolean) {
        showContent.set(status)
        showProgress.set(!status)
        showErrorMessage.set(!status)
    }

    /**
     * Method to show error message view
     * @param status : Boolean value for show and hide
     */
    fun showErrorMessageView(status: Boolean) {
        refreshModel.set(getDefaultErrorMessageDetails())
        showContent.set(!status)
        showProgress.set(!status)
        showErrorMessage.set(status)
    }

    /**
     * Method to get default error message details
     */
    private fun getDefaultErrorMessageDetails(): RefreshModel = RefreshModel(
        ObservableField(appContext.getString(R.string.error_title)),
        ObservableField(appContext.getString(R.string.error_message)),
        ObservableField(appContext.getString(R.string.retry))
    )

    /**
     * Method to show progress view
     */
    fun showProgressView(status: Boolean) {
        showContent.set(true)
        showProgress.set(status)
        showErrorMessage.set(false)
    }

    /**
     * open click listener for retry button on error message view
     */
    open fun onClickRetryButton(view: View) { /*  Do not delete this method */ }

    /**
     * Click listener for header left icon
     */
    open fun onClickHeaderLeftIcon(view: View) { /*  Do not delete this method */ }

    /**
     * Method to check internet connectivity
     */
    fun checkInternetConnectivity(): Boolean {
        if (NetworkUtils.isInternetAvailable(appContext)) return true
        showErrorMessageView(true)
        return false
    }

    /**
     * Method to check API success response
     */
    fun checkApiResponse(responseCode: Int): Any? {
        when (responseCode) {
            in 200..299 -> return null
            in 500..599 -> return getDefaultErrorMessageDetails() // Server error
            401 -> return getDefaultErrorMessageDetails() //doLogout()
            else -> return getDefaultErrorMessageDetails() // REFRESH_ITEM_TYPE_UNKNOWN_ERROR
        }
    }

    /**
     * Method to check API failure response
     */
    /*fun checkApiFailure(exception: Throwable): RefreshItem = when (exception) {
        is SocketTimeoutException -> getRefreshItem(AppConstUtils.REFRESH_ITEM_TYPE_TIME_OUT)
        is IOException -> getRefreshItem(AppConstUtils.REFRESH_ITEM_TYPE_NO_INTERNET)
        is UnknownHostException, is SSLPeerUnverifiedException, is SSLHandshakeException, is SSLException ->
            getRefreshItem(AppConstUtils.REFRESH_ITEM_TYPE_NO_INTERNET)
        else -> getDefaultErrorMessageDetails()
    }*/
}