package com.mobile.proyectofinal.data

import com.mobile.proyectofinal.data.db.NewsDao
import com.mobile.proyectofinal.data.enitty.News
import com.mobile.proyectofinal.data.enitty.NewsResponse
import com.mobile.proyectofinal.data.network.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class NewsRepository(
    private val newsDao: NewsDao
) {
    suspend fun getNews(country: String, pageSize: Int): List<News> {
        val apiResponse = RetrofitInstance.newsApiService.getTopHeadLines(country, pageSize).body()
        checkApiResponse(apiResponse)
        return apiResponse?.articles ?: emptyList()
    }

    suspend fun searchNews(searchQuery: String, pageNumber: Int): List<News> {
        val apiResponse = RetrofitInstance.newsApiService.searchNews(searchQuery, pageNumber).body()
        checkApiResponse(apiResponse)
        return apiResponse?.articles ?: emptyList()
    }

    suspend fun getNewsByTitle(title: String) = newsDao.getNewsByTitle(title)

    suspend fun deleteNews(news: News) = newsDao.delete(news)

    suspend fun insertFavouriteNew(news: News) = newsDao.insert(news)

    suspend fun updateNews(news: News) = newsDao.update(news)

    fun getFavouriteNews(): Flow<List<News>> = newsDao.getFavoriteNews()

    fun getReadNews(): Flow<List<News>> = newsDao.getNewsRead()

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
