package com.example.finalproject

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface Apinterface {
//    @GET("apod?date={date}&api_key=DEMO_KEY")
    @GET
    fun getData(@Url url:String): Call<Pic>
}