package com.example.rssreader.data.source

import com.example.rssreader.data.source.model.Article
import com.example.rssreader.data.source.model.Feed
import com.example.rssreader.utils.Kind
import io.reactivex.Completable
import io.reactivex.Flowable

interface ArticleDataSource {
    interface Remote {
        fun getNewsArticles(url: String?, kind: Kind): Flowable<Feed>
    }

    interface Local {
        fun saveArticle(vararg article: Article): Completable

        fun getLocalArticles(kind: Kind): Flowable<MutableList<Article>>
    }
}
