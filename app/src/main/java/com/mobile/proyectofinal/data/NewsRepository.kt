package com.mobile.proyectofinal.data

import com.mobile.proyectofinal.model.News
import com.mobile.proyectofinal.model.NewsResponse
import com.mobile.proyectofinal.network.NewsApi
import com.mobile.proyectofinal.network.RetrofitInstance

class NewsRepository {
    private val newsService = RetrofitInstance.newsApiService
    private var news: List<News> = emptyList()

    suspend fun getNews(country: String, pageSize: Int): List<News> {
        val apiResponse = newsService.getTopHeadLines(country, pageSize).body()
        if (apiResponse?.status == "error") {
            when (apiResponse.code) {
                "apiKeyMissing" -> throw MissingApiKeyException()
                "apiKeyInvalid" -> throw ApiKeyInvalidException()
                else -> throw Exception()
            }
        }
        news = apiResponse?.articles ?: emptyList()
        return news
    }
}

class MissingApiKeyException : java.lang.Exception()
class ApiKeyInvalidException : java.lang.Exception()