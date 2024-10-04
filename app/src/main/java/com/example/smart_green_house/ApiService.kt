package com.example.smart_green_house

import com.example.smart_green_house.Response.KelembapanResponse
import com.example.smart_green_house.Response.KelembapanTnhResponse
import com.example.smart_green_house.Response.MessageResponse
import com.example.smart_green_house.Response.SuhuResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("bacasuhu")
    fun getSuhu(): Call<SuhuResponse>

    @GET("bacakelembapan")
    fun getkelembapan(): Call<KelembapanResponse>

    @GET("bacakelembapanTnh")
    fun getkelembapanTnh(): Call<KelembapanTnhResponse>

    @GET("on")
    fun getTurnOnLamp(): Call<MessageResponse>

    @GET("off")
    fun getTurnOffLamp(): Call<MessageResponse>

    @GET("waterOn")
    fun getTurnOnWater(): Call<MessageResponse>

    @GET("waterOff")
    fun getTurnOffwater(): Call<MessageResponse>

}