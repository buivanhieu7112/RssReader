package com.example.rssreader.data.source

import com.example.rssreader.data.source.model.Feed
import io.reactivex.Flowable

interface ArticleDataSource {
    interface Remote {
        fun getArticles(): Flowable<Feed>
    }
}
