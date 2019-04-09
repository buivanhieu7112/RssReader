package com.example.rssreader.data.source

import com.example.rssreader.data.source.model.Feed
import io.reactivex.Flowable

interface ArticleDataSource {
    interface Remote {
        fun getHomeArticles(): Flowable<Feed>

        fun getNewsArticles(): Flowable<Feed>

        fun getWorldArticles(): Flowable<Feed>

        fun getBusinessArticles(): Flowable<Feed>

        fun getStartUpArticles(): Flowable<Feed>

        fun getEntertainmentArticles(): Flowable<Feed>

        fun getHomeArticles24h(): Flowable<Feed>

        fun getNewsArticles24h(): Flowable<Feed>

        fun getWorldArticles24h(): Flowable<Feed>

        fun getBusinessArticles24h(): Flowable<Feed>

        fun getStartUpArticles24h(): Flowable<Feed>

        fun getEntertainmentArticles24h(): Flowable<Feed>
    }
}
