package com.mobile.proyectofinal.model.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mobile.proyectofinal.model.enitty.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(news: News)

    @Update
    suspend fun update(news: News)

    @Delete
    suspend fun delete(news: News)

    @Query("SELECT * FROM news")
    fun getNews(): Flow<List<News>>
}