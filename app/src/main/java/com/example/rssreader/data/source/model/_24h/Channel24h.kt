package com.example.rssreader.data.source.model._24h

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
class Channel24h {
    @field:ElementList(inline = true)
    var item24h: MutableList<Article24h>? = null
}
