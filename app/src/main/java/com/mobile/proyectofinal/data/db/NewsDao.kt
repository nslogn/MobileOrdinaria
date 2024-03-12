package com.mobile.proyectofinal.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mobile.proyectofinal.data.enitty.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: News)

    @Update
    suspend fun update(news: News)

    @Delete
    suspend fun delete(news: News)

    @Query("SELECT * FROM news")
    fun getNews(): Flow<List<News>>

    @Query("SELECT * FROM news  WHERE isRead = 1")
    fun getNewsRead(): Flow<List<News>>

    @Query("SELECT * FROM news  WHERE isFavorite = 1")
    fun getFavoriteNews(): Flow<List<News>>

    @Query("SELECT * FROM news WHERE title = :title")
    suspend fun getNewsByTitle(title: String): News?
}