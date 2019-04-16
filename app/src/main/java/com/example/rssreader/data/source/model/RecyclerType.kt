package com.example.rssreader.data.source.model

import androidx.annotation.IntDef

@IntDef(TYPE_UNKNOWN, TYPE_ARTICLE, TYPE_ARTICLE_24)
@Retention(AnnotationRetention.SOURCE)
annotation class RecyclerType

const val TYPE_UNKNOWN = -1
const val TYPE_ARTICLE = 0
const val TYPE_ARTICLE_24 = 1