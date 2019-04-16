package com.example.rssreader.data.source.remote

import com.example.rssreader.data.source.ArticleDataSource
import com.example.rssreader.data.source.model.VnExpress.Feed
import com.example.rssreader.data.source.model._24h.Feed24h
import io.reactivex.Flowable
import javax.inject.Inject

class ArticleRemoteDataSource @Inject constructor(
    private val apiVnExpress: ApiVnExpress,
    private val api24h: Api24h
) : ArticleDataSource.Remote {
    override fun getHomeArticles(): Flowable<Feed> {
        return apiVnExpress.articlesVnExpress
    }

    override fun getNewsArticles(): Flowable<Feed> {
        return apiVnExpress.newsVnExpress
    }

    override fun getWorldArticles(): Flowable<Feed> {
        return apiVnExpress.worldVnExpress
    }

    override fun getBusinessArticles(): Flowable<Feed> {
        return apiVnExpress.businessVnExpress
    }

    override fun getStartUpArticles(): Flowable<Feed> {
        return apiVnExpress.startupVnExpress
    }

    override fun getEntertainmentArticles(): Flowable<Feed> {
        return apiVnExpress.entertainmentVnExpress
    }

    override fun getHomeArticles24h(): Flowable<Feed24h> {
        return api24h.articles24h
    }

    override fun getNewsArticles24h(): Flowable<Feed24h> {
        return api24h.news24h
    }

    override fun getWorldArticles24h(): Flowable<Feed24h> {
        return api24h.world24h
    }

    override fun getBusinessArticles24h(): Flowable<Feed24h> {
        return api24h.business24h
    }

    override fun getStartUpArticles24h(): Flowable<Feed24h> {
        return api24h.startup24h
    }

    override fun getEntertainmentArticles24h(): Flowable<Feed24h> {
        return api24h.entertainment24h
    }
}
