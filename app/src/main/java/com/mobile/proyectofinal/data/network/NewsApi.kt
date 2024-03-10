package com.mobile.proyectofinal.data.network

import com.mobile.proyectofinal.data.enitty.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "43d3747c579347069f2e1f953fe2183e"


interface NewsApi {
    @GET("top-headlines?apiKey=$API_KEY")
    suspend fun getTopHeadLines(
        @Query("country") country: String,
        @Query("pageSize") pageSize: Int
        ): Response<NewsResponse>
}