package com.example.rssreader.data.source.model

interface RecyclerViewItem {
    @setparam:RecyclerType
    var type: Int
        @RecyclerType get
}
