package com.example.rssreader.data.source.local.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rssreader.data.source.local.persistence.ArticleDatabase24h.Companion.DATABASE_VERSION
import com.example.rssreader.data.source.model.Article

@Database(entities = [Article::class], version = DATABASE_VERSION, exportSchema = false)
abstract class ArticleDatabase24h : RoomDatabase() {
    abstract fun article24hDao(): ArticleDao

    companion object {
        const val DATABASE_VERSION = 2
        const val DATABASE_NAME = "Article_Database_24h"
    }
}
