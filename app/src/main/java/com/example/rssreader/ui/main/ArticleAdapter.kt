package com.example.rssreader.ui.main

import android.util.Log
import android.view.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rssreader.R
import com.example.rssreader.data.source.model.Article
import com.example.rssreader.utils.ItemClickListener
import com.example.rssreader.utils.ItemContextMenuClickListener
import kotlinx.android.synthetic.main.adapter_article.view.*

class ArticleAdapter(
        private val itemClickListener: ItemClickListener,
        private val itemMenuClickListener: ItemContextMenuClickListener
) : ListAdapter<Article, ArticleAdapter.ArticleViewHolder>(ArticleDiffCallBack()) {
    class ArticleDiffCallBack : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
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
        private lateinit var article: Article

        init {
            itemView.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            val save: MenuItem = menu.add(Menu.NONE, 1, 1, "Save")
            val cancel: MenuItem = menu.add(Menu.NONE, 2, 2, "Cancel")
            save.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem?): Boolean {
                    Log.d("CONTEXT_MENU", "save ${article.link}")
                    itemMenuClickListener.onItemContextMenuClick(article)
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

        fun bind(article: Article, itemClickListener: ItemClickListener) {
            this.article = article
            itemView.textViewTitle.text = article.title
            itemView.textViewPubDate.text = article.pubDate

            itemView.setOnClickListener {
                itemClickListener.onItemClicked(article)
            }
        }
    }
}
