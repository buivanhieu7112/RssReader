package com.example.rssreader.data.source.remote

import com.example.rssreader.data.source.ArticleDataSource
import com.example.rssreader.data.source.model.Feed
import io.reactivex.Flowable
import javax.inject.Inject

class ArticleRemoteDataSource @Inject constructor(
    private val apiVnExpress: ApiVnExpress,
    private val api24h: Api24h
) : ArticleDataSource.Remote {
    override fun getHomeArticles(): Flowable<Feed> {
        return apiVnExpress.articles
    }

    override fun getNewsArticles(): Flowable<Feed> {
        return apiVnExpress.news
    }

    override fun getWorldArticles(): Flowable<Feed> {
        return apiVnExpress.world
    }

    override fun getBusinessArticles(): Flowable<Feed> {
        return apiVnExpress.business
    }

    override fun getStartUpArticles(): Flowable<Feed> {
        return apiVnExpress.startup
    }

    override fun getEntertainmentArticles(): Flowable<Feed> {
        return apiVnExpress.entertainment
    }

    override fun getHomeArticles24h(): Flowable<Feed> {
        return api24h.articles
    }

    override fun getNewsArticles24h(): Flowable<Feed> {
        return api24h.news
    }

    override fun getWorldArticles24h(): Flowable<Feed> {
        return api24h.world
    }

    override fun getBusinessArticles24h(): Flowable<Feed> {
        return api24h.business
    }

    override fun getStartUpArticles24h(): Flowable<Feed> {
        return api24h.startup
    }

    override fun getEntertainmentArticles24h(): Flowable<Feed> {
        return api24h.entertainment
    }
}
