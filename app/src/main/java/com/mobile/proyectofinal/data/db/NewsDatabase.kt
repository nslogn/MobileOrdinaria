package com.mobile.proyectofinal.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mobile.proyectofinal.data.enitty.News

@Database(
    entities = [News::class],
    version = 3,
    exportSchema = false, //so as not to keep schema version history backups
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        @Volatile //The value of a volatile variable is never cached, and all reads and writes are to and from the main memory.
        private var Instance: NewsDatabase? = null

        fun getDatabase(context: Context): NewsDatabase {
            val MIGRATION_1_2 = object : Migration(1, 2) {
                override fun migrate(db: SupportSQLiteDatabase) {
                    db.execSQL("DROP TABLE IF EXISTS `news`")
                    db.execSQL(
                        "CREATE TABLE IF NOT EXISTS `news` (" +
                                "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                "`title` TEXT NOT NULL, " +
                                "`content` TEXT, " +
                                "`author` TEXT, " +
                                "`url` TEXT NOT NULL, " +
                                "`urlToImage` TEXT, " +
                                "`isRead` INTEGER NOT NULL DEFAULT 0)"
                    )
                }
            }
            val MIGRATION_1_3 = object : Migration(2, 3) {
                override fun migrate(db: SupportSQLiteDatabase) {
                    db.execSQL("DROP TABLE IF EXISTS 'news'")
                    db.execSQL(
                        "CREATE TABLE IF NOT EXISTS `news` (" +
                                "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                "`title` TEXT NOT NULL, " +
                                "`content` TEXT, " +
                                "`author` TEXT, " +
                                "`url` TEXT NOT NULL, " +
                                "`urlToImage` TEXT, " +
                                "`isRead` INTEGER NOT NULL DEFAULT 0," +
                                "'isFavorite' INTEGER NOT NULL DEFAULT 0)"
                    )
                }
            }

            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, NewsDatabase::class.java, "news_database")
                    .addMigrations(MIGRATION_1_2)
                    .addMigrations(MIGRATION_1_3)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
