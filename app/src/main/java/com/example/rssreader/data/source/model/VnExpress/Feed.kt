package com.example.rssreader.data.source.model.VnExpress

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
class Feed {
    @field:Element(name = "channel")
    var channel: Channel? = null
}
