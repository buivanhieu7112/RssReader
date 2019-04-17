package com.example.rssreader.data.source.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.rssreader.data.source.model.RecyclerViewItem
import com.example.rssreader.data.source.model.TYPE_ARTICLE_VN_EXPRESS
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Entity(tableName = "ARTICLE")
@Root(name = "item", strict = false)
data class Article(
    @PrimaryKey
    @ColumnInfo
    var id: Long = System.currentTimeMillis(),
    @ColumnInfo
    @field:Element(name = "title") var title: String? = null, @ColumnInfo
    @field:Element(name = "link") var link: String? = null, @ColumnInfo
    @field:Element(name = "guid") var guid: String? = null, @ColumnInfo
    @field:Element(name = "pubDate") var pubDate: String? = null,
    @ColumnInfo var kind: Int? = null,
    override var type: Int = TYPE_UNKNOWN                               // define type item for multiple type
) : RecyclerViewItem
