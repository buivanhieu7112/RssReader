package com.example.rssreader.data.source

import com.example.rssreader.data.source.model.VnExpress.Article
import com.example.rssreader.data.source.model.VnExpress.Feed
import com.example.rssreader.data.source.model._24h.Article24h
import com.example.rssreader.data.source.model._24h.Feed24h
import io.reactivex.Completable
import io.reactivex.Flowable

class ArticleRepository(
    private val remote: ArticleDataSource.Remote,
    private val local: ArticleDataSource.Local
) : ArticleDataSource.Remote, ArticleDataSource.Local {

    override fun getHomeArticles(): Flowable<Feed> {
        return remote.getHomeArticles()
    }

    override fun getNewsArticles(): Flowable<Feed> {
        return remote.getNewsArticles()
    }

    override fun getWorldArticles(): Flowable<Feed> {
        return remote.getWorldArticles()
    }

    override fun getBusinessArticles(): Flowable<Feed> {
        return remote.getBusinessArticles()
    }

    override fun getStartUpArticles(): Flowable<Feed> {
        return remote.getStartUpArticles()
    }

    override fun getEntertainmentArticles(): Flowable<Feed> {
        return remote.getEntertainmentArticles()
    }

    override fun getHomeArticles24h(): Flowable<Feed24h> {
        return remote.getHomeArticles24h()
    }

    override fun getNewsArticles24h(): Flowable<Feed24h> {
        return remote.getNewsArticles24h()
    }

    override fun getWorldArticles24h(): Flowable<Feed24h> {
        return remote.getWorldArticles24h()
    }

    override fun getBusinessArticles24h(): Flowable<Feed24h> {
        return remote.getBusinessArticles24h()
    }

    override fun getStartUpArticles24h(): Flowable<Feed24h> {
        return remote.getStartUpArticles24h()
    }

    override fun getEntertainmentArticles24h(): Flowable<Feed24h> {
        return remote.getEntertainmentArticles24h()
    }

    override fun saveArticleVnExpress(vararg article: Article): Completable {
        return local.saveArticleVnExpress(*article)
    }

    override fun getLocalArticlesVnExpress(): Flowable<MutableList<Article>> {
        return local.getLocalArticlesVnExpress()
    }

    override fun saveArticle24h(vararg article24h: Article24h): Completable {
        return local.saveArticle24h(*article24h)
    }

    override fun getLocalArticles24h(): Flowable<MutableList<Article24h>> {
        return local.getLocalArticles24h()
    }
}
