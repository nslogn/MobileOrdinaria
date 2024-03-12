package com.mobile.proyectofinal.data.enitty

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class News(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var title: String,
    var content: String?,
    var author: String?,
    var url: String,
    var urlToImage: String?,
    var isRead: Boolean = false
)