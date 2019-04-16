package com.example.rssreader.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rssreader.base.BaseViewModel
import com.example.rssreader.data.source.ArticleRepository
import com.example.rssreader.data.source.model.RecyclerViewItem
import com.example.rssreader.data.source.model.VnExpress.Article
import com.example.rssreader.data.source.model._24h.Article24h
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val articleRepository: ArticleRepository) : BaseViewModel() {
    var liveData = MutableLiveData<List<RecyclerViewItem>>()

    fun getHomeArticles() {
        launchDisposable(
            articleRepository.getHomeArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    liveData.value = result.channel?.item
                    Log.d("DATA", result.channel?.item?.size.toString())
                }, { error -> Log.e("ERROR", error.localizedMessage.toString()) })
        )
    }

    fun getNewsArticles() {
        launchDisposable(
            articleRepository.getNewsArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    liveData.value = result.channel?.item
                }, { error -> Log.e("ERROR", error.localizedMessage.toString()) })
        )
    }

    fun getWorldArticles() {
        launchDisposable(
            articleRepository.getWorldArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    liveData.value = result.channel?.item
                }, { error -> Log.e("ERROR", error.localizedMessage.toString()) })
        )
    }

    fun getBusinessArticles() {
        launchDisposable(
            articleRepository.getBusinessArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    liveData.value = result.channel?.item
                }, { error -> Log.e("ERROR", error.localizedMessage.toString()) })
        )
    }

    fun getStartUpArticles() {
        launchDisposable(
            articleRepository.getStartUpArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    liveData.value = result.channel?.item
                }, { error -> Log.e("ERROR", error.localizedMessage.toString()) })
        )
    }

    fun getEntertainmentArticles() {
        launchDisposable(
            articleRepository.getEntertainmentArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    liveData.value = result.channel?.item
                }, { error -> Log.e("ERROR", error.localizedMessage.toString()) })
        )
    }

    fun saveArticle(vararg article: Article) {
        articleRepository.saveArticleVnExpress(*article)
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

    fun getLocalArticles() {
        launchDisposable(
            articleRepository.getLocalArticlesVnExpress()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    liveData.value = result
                }, { error ->
                    Log.d("ERROR", error.localizedMessage)
                })
        )
    }

    fun getHomeArticles24h() {
        launchDisposable(
            articleRepository.getHomeArticles24h()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    liveData.value = result.channel24h!!.item24h
                }, { error -> Log.e("ERROR", error.localizedMessage.toString()) })
        )
    }

    fun getNewsArticles24h() {
        launchDisposable(
            articleRepository.getNewsArticles24h()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    liveData.value = result.channel24h!!.item24h
                }, { error -> Log.e("ERROR", error.localizedMessage.toString()) })
        )
    }

    fun getWorldArticles24h() {
        launchDisposable(
            articleRepository.getWorldArticles24h()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    liveData.value = result.channel24h!!.item24h
                }, { error -> Log.e("ERROR", error.localizedMessage.toString()) })
        )
    }

    fun getBusinessArticles24h() {
        launchDisposable(
            articleRepository.getBusinessArticles24h()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    liveData.value = result.channel24h!!.item24h
                }, { error -> Log.e("ERROR", error.localizedMessage.toString()) })
        )
    }

    fun getStartUpArticles24h() {
        launchDisposable(
            articleRepository.getStartUpArticles24h()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    liveData.value = result.channel24h!!.item24h
                }, { error -> Log.e("ERROR", error.localizedMessage.toString()) })
        )
    }

    fun getEntertainmentArticles24h() {
        launchDisposable(
            articleRepository.getEntertainmentArticles24h()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    liveData.value = result.channel24h!!.item24h
                }, { error -> Log.e("ERROR", error.localizedMessage.toString()) })
        )
    }

    fun saveArticle24h(vararg article: Article24h) {
        articleRepository.saveArticle24h(*article)
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

    fun getLocalArticles24h() {
        launchDisposable(
            articleRepository.getLocalArticles24h()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> liveData.value = result }, { error ->
                    Log.d("ERROR", error.localizedMessage)
                })
        )
    }
}
