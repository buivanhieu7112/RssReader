package com.example.rssreader.data.source

import com.example.rssreader.data.source.model.VnExpress.Article
import com.example.rssreader.data.source.model.VnExpress.Feed
import com.example.rssreader.data.source.model._24h.Article24h
import com.example.rssreader.data.source.model._24h.Feed24h
import io.reactivex.Completable
import io.reactivex.Flowable

interface ArticleDataSource {
    interface Remote {
        fun getHomeArticles(): Flowable<Feed>

        fun getNewsArticles(): Flowable<Feed>

        fun getWorldArticles(): Flowable<Feed>

        fun getBusinessArticles(): Flowable<Feed>

        fun getStartUpArticles(): Flowable<Feed>

        fun getEntertainmentArticles(): Flowable<Feed>

        fun getHomeArticles24h(): Flowable<Feed24h>

        fun getNewsArticles24h(): Flowable<Feed24h>

        fun getWorldArticles24h(): Flowable<Feed24h>

        fun getBusinessArticles24h(): Flowable<Feed24h>

        fun getStartUpArticles24h(): Flowable<Feed24h>

        fun getEntertainmentArticles24h(): Flowable<Feed24h>
    }

    interface Local {
        fun saveArticleVnExpress(vararg article: Article): Completable

        fun getLocalArticlesVnExpress(): Flowable<MutableList<Article>>

        fun saveArticle24h(vararg article24h: Article24h): Completable

        fun getLocalArticles24h(): Flowable<MutableList<Article24h>>
    }
}
