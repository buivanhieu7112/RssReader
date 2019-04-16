package com.example.rssreader.data.source.local

import com.example.rssreader.data.source.ArticleDataSource
import com.example.rssreader.data.source.local.persistence.ArticleDatabase
import com.example.rssreader.data.source.model.VnExpress.Article
import com.example.rssreader.data.source.model._24h.Article24h
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class ArticleLocalDataSource @Inject constructor(private val articleDatabase: ArticleDatabase) :
    ArticleDataSource.Local {
    override fun saveArticleVnExpress(vararg article: Article): Completable {
        return articleDatabase.articleDao().insertArticleVnExpress(*article)
    }

    override fun getLocalArticlesVnExpress(): Flowable<MutableList<Article>> {
        return articleDatabase.articleDao().getArticlesVnExpress()
    }

    override fun saveArticle24h(vararg article24h: Article24h): Completable {
        return articleDatabase.articleDao().insertArticle24h(*article24h)
    }

    override fun getLocalArticles24h(): Flowable<MutableList<Article24h>> {
        return articleDatabase.articleDao().getArticles24h()
    }
}
