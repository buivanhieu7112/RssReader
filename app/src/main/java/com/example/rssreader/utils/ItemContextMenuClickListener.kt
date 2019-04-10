package com.example.rssreader.utils

import com.example.rssreader.data.source.model.Article

interface ItemContextMenuClickListener {
    fun onItemContextMenuClick(article: Article)
}
