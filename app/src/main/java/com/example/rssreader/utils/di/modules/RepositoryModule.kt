package com.example.rssreader.utils.di.modules

import com.example.rssreader.data.source.ArticleRepository
import com.example.rssreader.data.source.remote.ApiVnExpress
import com.example.rssreader.data.source.remote.ArticleRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesArticleRepository(apiVnExpress: ApiVnExpress): ArticleRepository {
        return ArticleRepository(ArticleRemoteDataSource(apiVnExpress))
    }
}
