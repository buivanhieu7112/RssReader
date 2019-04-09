package com.example.rssreader.utils.di.modules

import com.example.rssreader.data.source.remote.Api24h
import com.example.rssreader.data.source.remote.ApiVnExpress
import com.example.rssreader.utils.Constant
import com.example.rssreader.utils.ServiceGenerator
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gSonBuilder = GsonBuilder()
        gSonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gSonBuilder.create()
    }

//    @Provides
//    @Singleton
//    fun provideRetrofit(gson: Gson): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(Constant.BASE_URL)
//            .addConverterFactory(SimpleXmlConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideApiService(retrofit: Retrofit): ApiVnExpress {
//        return retrofit.create(ApiVnExpress::class.java)
//    }

    @Provides
    @Singleton
    fun provideApi24h(): Api24h {
        return ServiceGenerator.generate(Constant.BASE_URL_24h, Api24h::class.java)
    }

    @Provides
    @Singleton
    fun provideApiVnExpress(): ApiVnExpress {
        return ServiceGenerator.generate(Constant.BASE_URL, ApiVnExpress::class.java)
    }
}
