package com.example.rssreader.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rssreader.base.BaseViewModel
import com.example.rssreader.data.source.ArticleRepository
import com.example.rssreader.data.source.model.Article
import com.example.rssreader.data.source.model.RecyclerViewItem
import com.example.rssreader.utils.Kind
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val articleRepository: ArticleRepository) : BaseViewModel() {
    var liveData = MutableLiveData<List<RecyclerViewItem>>()

    fun getNewsArticles(url: String?, kind: Kind) {
        launchDisposable(
            articleRepository.getNewsArticles(url, kind)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    liveData.value = result.channel?.item
                }, { error -> Log.e("ERROR", error.localizedMessage.toString()) })
        )
    }

    fun saveArticle(vararg article: Article) {
        articleRepository.saveArticle(*article)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onComplete() {
                    Log.d("SUCCESS", "INSERT ARTICLE SUCCESSFUL")
                    Log.d("WEB_OFF", article[0].guid)
                }

                override fun onSubscribe(d: Disposable) {
                    launchDisposable(d)
                }

                override fun onError(e: Throwable) {
                    Log.d("ERROR", e.localizedMessage)
                }
            })
    }

    fun getLocalArticles(kind: Kind) {
        launchDisposable(
            articleRepository.getLocalArticles(kind)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    liveData.value = result
                }, { error ->
                    Log.d("ERROR", error.localizedMessage)
                })
        )
    }
}
