package com.example.rssreader.data.source

import com.example.rssreader.data.source.model.Article
import com.example.rssreader.data.source.model.Feed
import com.example.rssreader.utils.Kind
import io.reactivex.Completable
import io.reactivex.Flowable

class ArticleRepository(
    private val remote: ArticleDataSource.Remote,
    private val local: ArticleDataSource.Local
) : ArticleDataSource.Remote, ArticleDataSource.Local {
    override fun getNewsArticles(url: String?, kind: Kind): Flowable<Feed> {
        return remote.getNewsArticles(url, kind)
    }

    override fun saveArticle(vararg article: Article): Completable {
        return local.saveArticle(*article)
    }

    override fun getLocalArticles(kind: Kind): Flowable<MutableList<Article>> {
        return local.getLocalArticles(kind)
    }
}
