package com.example.rssreader.data.source.model._24h

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rssreader.data.source.model.RecyclerViewItem
import com.example.rssreader.data.source.model.TYPE_ARTICLE
import com.example.rssreader.data.source.model.TYPE_ARTICLE_24
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Entity(tableName = "ARTICLE_24H")
@Root(name = "item", strict = false)
data class Article24h(
    @PrimaryKey
    @ColumnInfo
    var id: Long = System.currentTimeMillis(),
    @ColumnInfo
    @field:Element(name = "title") var title: String? = null, @ColumnInfo
    @field:Element(name = "link") var link: String? = null, @ColumnInfo
    @field:Element(name = "guid") var guid: String? = null, @ColumnInfo
    @field:Element(name = "pubDate") var pubDate: String? = null, override var type: Int = TYPE_ARTICLE_24
) : RecyclerViewItem
