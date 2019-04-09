package com.example.rssreader.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rssreader.base.BaseViewModel
import com.example.rssreader.data.source.ArticleRepository
import com.example.rssreader.data.source.model.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val articleRepository: ArticleRepository) : BaseViewModel() {
    var liveData = MutableLiveData<MutableList<Article>>()

    fun getHomeArticles() {
        launchDisposable(
            articleRepository.getHomeArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    liveData.value = result.channel!!.item
                }, { error -> Log.e("ERROR", error.localizedMessage.toString()) })
        )
    }

    fun getNewsArticles() {
        launchDisposable(
            articleRepository.getNewsArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    liveData.value = result.channel!!.item
                }, { error -> Log.e("ERROR", error.localizedMessage.toString()) })
        )
    }

    fun getWorldArticles() {
        launchDisposable(
            articleRepository.getWorldArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    liveData.value = result.channel!!.item
                }, { error -> Log.e("ERROR", error.localizedMessage.toString()) })
        )
    }

    fun getBusinessArticles() {
        launchDisposable(
            articleRepository.getBusinessArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    liveData.value = result.channel!!.item
                }, { error -> Log.e("ERROR", error.localizedMessage.toString()) })
        )
    }

    fun getStartUpArticles() {
        launchDisposable(
            articleRepository.getStartUpArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    liveData.value = result.channel!!.item
                }, { error -> Log.e("ERROR", error.localizedMessage.toString()) })
        )
    }

    fun getEntertainmentArticles() {
        launchDisposable(
            articleRepository.getEntertainmentArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    liveData.value = result.channel!!.item
                }, { error -> Log.e("ERROR", error.localizedMessage.toString()) })
        )
    }
}
