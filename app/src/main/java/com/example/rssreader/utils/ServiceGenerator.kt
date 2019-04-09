package com.example.rssreader.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

class ServiceGenerator {

    companion object {
        private const val CONNECTION_TIMEOUT = 15L
        fun <T> generate(baseUrl: String, serviceClass: Class<T>): T {
            val okHttpClientBuilder = OkHttpClient().newBuilder()
            okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            okHttpClientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)

            val builder = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(SimpleXmlConverterFactory.create())
            val retrofit = builder.client(okHttpClientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(serviceClass)
        }
    }
}
