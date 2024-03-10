package com.mobile.proyectofinal.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobile.proyectofinal.model.enitty.News

@Database(
    entities = [News::class],
    version = 1,
    exportSchema = false //so as not to keep schema version history backups
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        @Volatile //The value of a volatile variable is never cached, and all reads and writes are to and from the main memory.
        private var Instance: NewsDatabase? = null

        fun getDatabase(context: Context): NewsDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, NewsDatabase::class.java, "news_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
