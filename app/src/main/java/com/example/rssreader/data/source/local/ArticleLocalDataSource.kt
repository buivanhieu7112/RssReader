package com.example.rssreader.data.source.local

import com.example.rssreader.data.source.ArticleDataSource
import com.example.rssreader.data.source.local.persistence.ArticleDao
import com.example.rssreader.data.source.model.Article
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class ArticleLocalDataSource @Inject constructor(private val articleDao: ArticleDao, private val articleDao24h: ArticleDao) : ArticleDataSource.Local {
    override fun saveArticle(vararg article: Article): Completable {
        return articleDao.insertArticle(*article)
    }

    override fun getLocalArticles(): Flowable<MutableList<Article>> {
        return articleDao.getArticles()
    }

    override fun saveArticle24h(vararg article: Article): Completable {
        return articleDao24h.insertArticle(*article)
    }

    override fun getLocalArticles24h(): Flowable<MutableList<Article>> {
        return articleDao24h.getArticles()
    }
}
