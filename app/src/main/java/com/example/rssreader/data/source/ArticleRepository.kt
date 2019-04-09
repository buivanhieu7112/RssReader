package com.example.rssreader.data.source

import com.example.rssreader.data.source.model.Feed
import io.reactivex.Flowable

class ArticleRepository(private val remote: ArticleDataSource.Remote) : ArticleDataSource.Remote {
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
}
