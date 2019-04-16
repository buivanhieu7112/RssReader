package com.example.rssreader.data.source.remote

import com.example.rssreader.data.source.model.VnExpress.Feed
import com.example.rssreader.utils.Constant
import io.reactivex.Flowable
import retrofit2.http.GET

interface ApiVnExpress {
    @get:GET(Constant.HomeVnExpress)
    var articlesVnExpress: Flowable<Feed>

    @get:GET(Constant.NewsVnExpress)
    var newsVnExpress: Flowable<Feed>

    @get:GET(Constant.WorldVnExpress)
    var worldVnExpress: Flowable<Feed>

    @get:GET(Constant.BusinessVnExpress)
    var businessVnExpress: Flowable<Feed>

    @get:GET(Constant.StartupVnExpress)
    var startupVnExpress: Flowable<Feed>

    @get:GET(Constant.EntertainmentVnExpress)
    var entertainmentVnExpress: Flowable<Feed>
}
