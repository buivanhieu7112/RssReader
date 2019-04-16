package com.example.rssreader.data.source.remote

import com.example.rssreader.data.source.model._24h.Feed24h
import com.example.rssreader.utils.Constant
import io.reactivex.Flowable
import retrofit2.http.GET

interface Api24h {
    @get:GET(Constant.Home24h)
    var articles24h: Flowable<Feed24h>

    @get:GET(Constant.News24h)
    var news24h: Flowable<Feed24h>

    @get:GET(Constant.World24h)
    var world24h: Flowable<Feed24h>

    @get:GET(Constant.Business24h)
    var business24h: Flowable<Feed24h>

    @get:GET(Constant.Startup24h)
    var startup24h: Flowable<Feed24h>

    @get:GET(Constant.Entertainment24h)
    var entertainment24h: Flowable<Feed24h>
}
