package com.example.rssreader.data.source

import com.example.rssreader.data.source.model.Article
import com.example.rssreader.data.source.model.Feed
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

    override fun getHomeArticles24h(): Flowable<Feed> {
        return remote.getHomeArticles24h()
    }

    override fun getNewsArticles24h(): Flowable<Feed> {
        return remote.getNewsArticles24h()
    }

    override fun getWorldArticles24h(): Flowable<Feed> {
        return remote.getWorldArticles24h()
    }

    override fun getBusinessArticles24h(): Flowable<Feed> {
        return remote.getBusinessArticles24h()
    }

    override fun getStartUpArticles24h(): Flowable<Feed> {
        return remote.getStartUpArticles24h()
    }

    override fun getEntertainmentArticles24h(): Flowable<Feed> {
        return remote.getEntertainmentArticles24h()
    }

    override fun saveArticle(vararg article: Article): Completable {
        return local.saveArticle(*article)
    }

    override fun getLocalArticles(): Flowable<MutableList<Article>> {
        return local.getLocalArticles()
    }

    override fun saveArticle24h(vararg article: Article): Completable {
        return local.saveArticle24h(*article)
    }

    override fun getLocalArticles24h(): Flowable<MutableList<Article>> {
        return local.getLocalArticles24h()
    }
}
