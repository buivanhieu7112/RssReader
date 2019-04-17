package com.example.rssreader.data.source.model

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
class Channel {
    @field:ElementList(inline = true, required = false)
    var item: MutableList<Article>? = null
}
