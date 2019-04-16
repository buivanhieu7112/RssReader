package com.example.rssreader.data.source.model._24h

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
class Feed24h {
    @field:Element(name = "channel")
    var channel24h: Channel24h? = null
}
