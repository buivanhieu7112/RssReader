package com.example.rssreader.data.source.remote

import com.example.rssreader.data.source.model.Feed
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiVnExpress {
    @GET("{url}")
    fun newsArticle(@Path("url") url: String?): Flowable<Feed>
}
