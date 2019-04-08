package com.example.rssreader.data.source

import com.example.rssreader.data.source.model.Feed
import io.reactivex.Flowable

class ArticleRepository(private val remote: ArticleDataSource.Remote) : ArticleDataSource.Remote {
    override fun getArticles(): Flowable<Feed> {
        return remote.getArticles()
    }
}
