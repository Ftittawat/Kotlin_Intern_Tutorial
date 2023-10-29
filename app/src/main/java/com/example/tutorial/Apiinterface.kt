package com.example.tutorial

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("3/tv/popular")
    fun getData(@Query("page") page: Int): Call<TVPopularData>

    @GET("3/tv/popular")
    fun getDataPaging(@Query("page") page: Int): TVPopularData
}

// 3/tv/popular
// api/Cases/today-cases-all