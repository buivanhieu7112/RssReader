package com.example.rssreader.data.source.local.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rssreader.data.source.model.Article

@Database(entities = [Article::class], version = ArticleDatabase.DATABASE_VERSION)
abstract class ArticleDatabase24h : RoomDatabase() {
    abstract fun article24hDao(): ArticleDao

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Article_Database_24h"
    }
}
