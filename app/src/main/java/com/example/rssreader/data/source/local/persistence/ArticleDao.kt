package com.example.rssreader.data.source.local.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rssreader.data.source.model.Article
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(vararg article: Article): Completable

    @Query("SELECT * FROM ARTICLE")
    fun getArticles(): Flowable<MutableList<Article>>
}
