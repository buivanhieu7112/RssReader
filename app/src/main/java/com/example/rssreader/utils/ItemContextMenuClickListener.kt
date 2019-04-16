package com.example.rssreader.utils

import com.example.rssreader.data.source.model.VnExpress.Article
import com.example.rssreader.data.source.model._24h.Article24h

interface ItemContextMenuClickListener {
    fun onItemContextMenuClick(article: Article)

    fun onItemContextMenu24hClick(article24h: Article24h)
}
