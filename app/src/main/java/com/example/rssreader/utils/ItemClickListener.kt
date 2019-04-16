package com.example.rssreader.utils

import com.example.rssreader.data.source.model.VnExpress.Article
import com.example.rssreader.data.source.model._24h.Article24h

interface ItemClickListener {
    fun onItemClicked(article: Article)

    fun onItem24hClicked(article24h: Article24h)
}
