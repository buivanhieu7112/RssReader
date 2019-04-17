package com.example.rssreader.data.source.local.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rssreader.data.source.model.Article
import com.example.rssreader.data.source.model.TYPE_ARTICLE_24H
import com.example.rssreader.data.source.model.TYPE_ARTICLE_VN_EXPRESS
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(vararg article: Article): Completable

    @Query("SELECT * FROM ARTICLE WHERE kind = $TYPE_ARTICLE_VN_EXPRESS")
    fun getArticlesVnExpress(): Flowable<MutableList<Article>>

    @Query("SELECT * FROM ARTICLE WHERE kind = $TYPE_ARTICLE_24H")
    fun getArticles24h(): Flowable<MutableList<Article>>
}
