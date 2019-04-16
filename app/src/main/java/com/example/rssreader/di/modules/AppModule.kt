package com.example.rssreader.di.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.rssreader.data.source.local.persistence.ArticleDatabase
import com.example.rssreader.data.source.local.persistence.ArticleDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideArticleDatabase(application: Application): ArticleDatabase {
        return Room.databaseBuilder(application.applicationContext, ArticleDatabase::class.java, DATABASE_NAME).build()
    }

//    @Provides
//    @Singleton
//    fun provideArticleDatabase24h(application: Application): ArticleDatabase24h {
//        return Room.databaseBuilder(
//            application.applicationContext,
//            ArticleDatabase24h::class.java,
//            ArticleDatabase24h.DATABASE_NAME
//        ).build()
//    }
}
