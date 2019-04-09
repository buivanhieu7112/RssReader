package com.example.rssreader.data.source.remote

import com.example.rssreader.data.source.model.Feed
import io.reactivex.Flowable
import retrofit2.http.GET

interface Api24h {
    @get:GET("/upload/rss/trangchu24h.rss")
    var articles: Flowable<Feed>

    @get:GET("/upload/rss/tintuctrongngay.rss")
    var news: Flowable<Feed>

    @get:GET("/upload/rss/bantrecuocsong.rss")
    var world: Flowable<Feed>

    @get:GET("/upload/rss/taichinhbatdongsan.rss")
    var business: Flowable<Feed>

    @get:GET("/upload/rss/giaoducduhoc.rss")
    var startup: Flowable<Feed>

    @get:GET("/upload/rss/cuoi24h.rss")
    var entertainment: Flowable<Feed>
}
