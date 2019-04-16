package com.example.rssreader.data.source.model.VnExpress

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
class Channel {
    @field:ElementList(inline = true)
    var item: MutableList<Article>? = null
}
