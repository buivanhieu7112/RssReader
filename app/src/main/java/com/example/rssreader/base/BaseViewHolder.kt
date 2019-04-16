package com.example.rssreader.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rssreader.data.source.model.RecyclerViewItem

open class BaseViewHolder<T : RecyclerViewItem>(itemView: View) : RecyclerView.ViewHolder(
    itemView) {

    protected val context: Context? = itemView.context

    protected var item: T? = null
        private set


    open fun bind(item: T) {
        this.item = item
    }
}