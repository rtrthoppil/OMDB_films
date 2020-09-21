package com.rtr.omdbfilms.utils

import com.rtr.omdbfilms.utils.AppConstUtils.API_CALL_CONNECT_TIME_OUT
import com.rtr.omdbfilms.utils.AppConstUtils.API_CALL_READ_TIME_OUT
import com.rtr.omdbfilms.utils.AppConstUtils.API_CALL_WRITE_TIME_OUT
import com.rtr.omdbfilms.utils.AppConstUtils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */


/**
 * Retrofit helper class for API calls
 */
object RetrofitHelper {

    private lateinit var retrofit : Retrofit

    /**
     * Method to create OkHttpClient for the project
     */
    private fun createClient(
        connectTimeOut: Long = API_CALL_CONNECT_TIME_OUT,
        readTimeOut: Long = API_CALL_READ_TIME_OUT,
        writeTimeOut: Long = API_CALL_WRITE_TIME_OUT
    ): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.addInterceptor { chain ->
            chain.proceed(chain.request().newBuilder().build())
        }.addInterceptor(httpLoggingInterceptor)
            .readTimeout(readTimeOut, TimeUnit.SECONDS)
            .writeTimeout(writeTimeOut, TimeUnit.SECONDS)
            .connectTimeout(connectTimeOut, TimeUnit.SECONDS)
        return httpClientBuilder.build()
    }

    /**
     * Method to get the retrofit instance
     */
    fun getRetrofitInstance() : Retrofit {
        if(RetrofitHelper::retrofit.isInitialized) return retrofit
        retrofit = Retrofit.Builder()
            .client(createClient()).baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit
    }
}