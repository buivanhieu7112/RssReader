package com.example.rssreader.data.source.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Entity(tableName = "ARTICLE")
@Root(name = "item", strict = false)
class Article {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id: Int? = null

    @ColumnInfo
    @field:Element(name = "title")
    var title: String? = null

    @ColumnInfo
    @field:Element(name = "link")
    var link: String? = null

    @ColumnInfo
    @field:Element(name = "guid")
    var guid: String? = null

    @ColumnInfo
    @field:Element(name = "pubDate")
    var pubDate: String? = null
}
