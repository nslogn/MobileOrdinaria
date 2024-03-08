package com.mobile.proyectofinal.network

import com.mobile.proyectofinal.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "fe050c83e88a4c9d93e6bff7842a1da1"


interface NewsApi {
    @GET("top-headlines?apiKey=$API_KEY")
    suspend fun getTopHeadLines(
        @Query("country") country: String,
        @Query("pageSize") pageSize: Int
        ): Response<NewsResponse>
}