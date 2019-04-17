package com.example.rssreader.data.source.model

import androidx.annotation.IntDef

@IntDef(TYPE_UNKNOWN, TYPE_ARTICLE_VN_EXPRESS, TYPE_ARTICLE_24H)
@Retention(AnnotationRetention.SOURCE)
annotation class RecyclerType

const val TYPE_UNKNOWN = -1
const val TYPE_ARTICLE_VN_EXPRESS = 0
const val TYPE_ARTICLE_24H = 1
