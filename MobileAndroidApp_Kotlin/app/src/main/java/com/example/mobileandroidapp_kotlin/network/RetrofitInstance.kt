package com.example.mobileandroidapp_kotlin.network

import ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:3000/api/"
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private const val Locations_URL = "https://vapi.vnappmob.com/api/"
    val apiLocations: ApiLocations by lazy {
        Retrofit.Builder()
            .baseUrl(Locations_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiLocations::class.java)
    }
}