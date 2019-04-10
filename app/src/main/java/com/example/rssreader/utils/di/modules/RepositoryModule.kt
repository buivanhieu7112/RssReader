package com.example.rssreader.utils.di.modules

import com.example.rssreader.data.source.ArticleRepository
import com.example.rssreader.data.source.local.ArticleLocalDataSource
import com.example.rssreader.data.source.local.persistence.ArticleDatabase
import com.example.rssreader.data.source.local.persistence.ArticleDatabase24h
import com.example.rssreader.data.source.remote.Api24h
import com.example.rssreader.data.source.remote.ApiVnExpress
import com.example.rssreader.data.source.remote.ArticleRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesArticleRepository(
        apiVnExpress: ApiVnExpress,
        api24h: Api24h,
        articleDatabase: ArticleDatabase,
        articleDatabase24h: ArticleDatabase24h
    ): ArticleRepository {
        return ArticleRepository(
            ArticleRemoteDataSource(apiVnExpress, api24h),
            ArticleLocalDataSource(articleDatabase.articleDao(), articleDatabase24h.article24hDao())
        )
    }
}
