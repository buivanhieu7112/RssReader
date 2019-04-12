package com.example.rssreader.data.source.remote

import com.example.rssreader.data.source.model.Feed
import io.reactivex.Flowable
import retrofit2.http.GET

interface ApiVnExpress {
    @get:GET("/rss/tin-moi-nhat.rss")
    var articles: Flowable<Feed>

    @get:GET("/rss/thoi-su.rss")
    var news: Flowable<Feed>

    @get:GET("/rss/the-gioi.rss")
    var world: Flowable<Feed>

    @get:GET("/rss/khoa-hoc.rss")
    var business: Flowable<Feed>

    @get:GET("/rss/startup.rss")
    var startup: Flowable<Feed>

    @get:GET("/rss/giai-tri.rss")
    var entertainment: Flowable<Feed>
}
