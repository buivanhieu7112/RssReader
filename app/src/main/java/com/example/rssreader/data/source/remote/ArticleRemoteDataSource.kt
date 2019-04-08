package com.example.rssreader.data.source.remote

import com.example.rssreader.data.source.ArticleDataSource
import com.example.rssreader.data.source.model.Feed
import io.reactivex.Flowable
import javax.inject.Inject

class ArticleRemoteDataSource @Inject constructor(private val apiVnExpress: ApiVnExpress) : ArticleDataSource.Remote {
    override fun getArticles(): Flowable<Feed> {
        return apiVnExpress.articles
    }
}
