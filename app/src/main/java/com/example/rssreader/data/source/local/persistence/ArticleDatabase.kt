package com.example.rssreader.data.source.local.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rssreader.data.source.local.persistence.ArticleDatabase.Companion.DATABASE_VERSION
import com.example.rssreader.data.source.model.VnExpress.Article
import com.example.rssreader.data.source.model._24h.Article24h

@Database(entities = [Article::class, Article24h::class], version = DATABASE_VERSION, exportSchema = false)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Article_Rss_Database"
    }
}
