package com.example.rssreader.data.source.local.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rssreader.data.source.local.persistence.ArticleDatabase.Companion.DATABASE_VERSION
import com.example.rssreader.data.source.model.Article

@Database(entities = [Article::class], version = DATABASE_VERSION, exportSchema = false)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Article_Database"
    }
}
