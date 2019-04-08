package com.example.rssreader.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rssreader.base.BaseViewModel
import com.example.rssreader.data.source.ArticleRepository
import com.example.rssreader.data.source.model.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val articleRepository: ArticleRepository) : BaseViewModel() {
    var liveData = MutableLiveData<MutableList<Article>>()

    fun getArticles() {
        val disposable: Disposable = articleRepository.getArticles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                liveData.value = result.channel!!.item
            }, { error -> Log.e("ERROR", error.localizedMessage.toString()) })
        launchDisposable(disposable)
    }
}
