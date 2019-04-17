package com.example.rssreader.utils

import com.example.rssreader.data.source.model.Article

interface ItemClickListener {
    fun onItemClicked(article: Article)
}
