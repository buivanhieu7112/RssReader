package com.example.rssreader.data.source.local.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rssreader.data.source.model.VnExpress.Article
import com.example.rssreader.data.source.model._24h.Article24h
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticleVnExpress(vararg article: Article): Completable

    @Query("SELECT * FROM ARTICLE")
    fun getArticlesVnExpress(): Flowable<MutableList<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle24h(vararg article: Article24h): Completable

    @Query("SELECT * FROM ARTICLE_24H")
    fun getArticles24h(): Flowable<MutableList<Article24h>>
}
