package com.example.rssreader.ui.main2

import android.util.Log
import android.view.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rssreader.R
import com.example.rssreader.data.source.model.VnExpress.Article
import com.example.rssreader.data.source.model._24h.Article24h
import com.example.rssreader.utils.ItemClickListener
import com.example.rssreader.utils.ItemContextMenuClickListener
import kotlinx.android.synthetic.main.adapter_article.view.*

class Article24hAdapter(
        private val itemClickListener: ItemClickListener,
        private val itemMenuClickListener: ItemContextMenuClickListener
) : ListAdapter<Article24h, Article24hAdapter.ArticleViewHolder>(ArticleDiffCallBack()) {
    class ArticleDiffCallBack : DiffUtil.ItemCallback<Article24h>() {
        override fun areItemsTheSame(oldItem: Article24h, newItem: Article24h): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article24h, newItem: Article24h): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_article, parent, false)
        return ArticleViewHolder(view, itemMenuClickListener)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    class ArticleViewHolder(itemView: View, val itemMenuClickListener: ItemContextMenuClickListener)
        : RecyclerView.ViewHolder(itemView), View.OnCreateContextMenuListener {
        private lateinit var article24h: Article24h

        init {
            itemView.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            val save: MenuItem = menu.add(Menu.NONE, 1, 1, "Save")
            val cancel: MenuItem = menu.add(Menu.NONE, 2, 2, "Cancel")
            save.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem?): Boolean {
                    Log.d("CONTEXT_MENU", "save ${article24h.link}")
                    itemMenuClickListener.onItemContextMenu24hClick(article24h)
                    return true
                }
            })
            cancel.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem?): Boolean {
                    Log.d("CONTEXT_MENU", "cancel")
                    return true
                }
            })
        }

        fun bind(article24h: Article24h, itemClickListener: ItemClickListener) {
            this.article24h = article24h
            itemView.textViewTitle.text = article24h.title
            itemView.textViewPubDate.text = article24h.pubDate

            itemView.setOnClickListener {
                itemClickListener.onItem24hClicked(article24h)
            }
        }
    }
}
