package com.mobile.proyectofinal.data

import com.mobile.proyectofinal.data.db.NewsDao
import com.mobile.proyectofinal.data.enitty.News
import com.mobile.proyectofinal.data.enitty.NewsResponse
import com.mobile.proyectofinal.data.network.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class NewsRepository(
    private val newsDao: NewsDao
) {
    private var news: List<News> = emptyList()

    suspend fun getNews(country: String, pageSize: Int): List<News> {
        val apiResponse = RetrofitInstance.newsApiService.getTopHeadLines(country, pageSize).body()
        checkApiResponse(apiResponse)
        news = apiResponse?.articles ?: emptyList()
        return news
    }

    suspend fun searchNews(searchQuery: String, pageNumber: Int): List<News> {
        val apiResponse = RetrofitInstance.newsApiService.searchNews(searchQuery, pageNumber).body()
        checkApiResponse(apiResponse)
        news = apiResponse?.articles ?: emptyList()
        return news
    }

    suspend fun insertFavouriteNew(new: News) = newsDao.insert(new)

    fun getFavouriteNews(): Flow<List<News>> = newsDao.getNews()

    private fun checkApiResponse(apiResponse: NewsResponse?) {
        if (apiResponse?.status == "error") {
            when (apiResponse.code) {
                "apiKeyMissing" -> throw MissingApiKeyException()
                "apiKeyInvalid" -> throw ApiKeyInvalidException()
                else -> throw Exception()
            }
        }
    }
}

class MissingApiKeyException : java.lang.Exception()
class ApiKeyInvalidException : java.lang.Exception()
