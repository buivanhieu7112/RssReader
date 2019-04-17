package com.example.rssreader.data.source.local

import com.example.rssreader.data.source.ArticleDataSource
import com.example.rssreader.data.source.local.persistence.ArticleDatabase
import com.example.rssreader.data.source.model.Article
import com.example.rssreader.utils.Kind
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class ArticleLocalDataSource @Inject constructor(private val articleDatabase: ArticleDatabase) :
    ArticleDataSource.Local {
    override fun saveArticle(vararg article: Article): Completable {
        return articleDatabase.articleDao().insertArticle(*article)
    }

    override fun getLocalArticles(kind: Kind): Flowable<MutableList<Article>> {
        return when (kind) {
            Kind.KIND_VN_EXPRESS -> articleDatabase.articleDao().getArticlesVnExpress()
            Kind.KIND_24H -> articleDatabase.articleDao().getArticles24h()
        }
    }
}
