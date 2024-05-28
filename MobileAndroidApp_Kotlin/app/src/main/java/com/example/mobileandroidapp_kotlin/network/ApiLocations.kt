package com.example.mobileandroidapp_kotlin.network

import com.example.mobileandroidapp_kotlin.model.District
import com.example.mobileandroidapp_kotlin.model.DistrictResponse
import com.example.mobileandroidapp_kotlin.model.Furnitures
import com.example.mobileandroidapp_kotlin.model.Province
import com.example.mobileandroidapp_kotlin.model.ProvinceResponse
import com.example.mobileandroidapp_kotlin.model.SignIn
import com.example.mobileandroidapp_kotlin.model.UserReponse
import com.example.mobileandroidapp_kotlin.model.Users
import com.example.mobileandroidapp_kotlin.model.Ward
import com.example.mobileandroidapp_kotlin.model.WardResponse
import com.example.mobileandroidapp_kotlin.model.YourDataModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiLocations {
    @GET("province")
    suspend fun getProvinces() : ProvinceResponse

    @GET("province/district/{province_id}")
    suspend fun getDistrict(@Path("province_id") province_id: Int) : DistrictResponse

    @GET("province/ward/{district_id}")
    suspend fun getWard(@Path("district_id") district_id: Int) : WardResponse


}
