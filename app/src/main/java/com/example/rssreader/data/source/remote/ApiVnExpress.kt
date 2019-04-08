package com.example.rssreader.data.source.remote

import com.example.rssreader.data.source.model.Feed
import io.reactivex.Flowable
import retrofit2.http.GET

interface ApiVnExpress {
    @get:GET("/rss/tin-moi-nhat.rss")
    var articles: Flowable<Feed>
}
