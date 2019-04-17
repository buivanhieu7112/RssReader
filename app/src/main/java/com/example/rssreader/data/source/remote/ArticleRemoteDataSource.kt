package com.example.rssreader.data.source.remote

import com.example.rssreader.data.source.ArticleDataSource
import com.example.rssreader.data.source.model.Feed
import com.example.rssreader.utils.Kind
import io.reactivex.Flowable
import javax.inject.Inject

class ArticleRemoteDataSource @Inject constructor(
    private val apiVnExpress: ApiVnExpress,
    private val api24h: Api24h
) : ArticleDataSource.Remote {
    override fun getNewsArticles(url: String?, kind: Kind): Flowable<Feed> {
        return when (kind) {
            Kind.KIND_VN_EXPRESS -> apiVnExpress.newsArticle(url)
            Kind.KIND_24H -> api24h.newsArticle(url)
        }
    }
}
