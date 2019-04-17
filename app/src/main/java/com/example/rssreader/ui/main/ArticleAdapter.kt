package com.example.rssreader.ui.main

import android.util.Log
import android.view.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.rssreader.R
import com.example.rssreader.base.BaseViewHolder
import com.example.rssreader.data.source.model.Article
import com.example.rssreader.data.source.model.RecyclerViewItem
import com.example.rssreader.data.source.model.TYPE_ARTICLE_24H
import com.example.rssreader.data.source.model.TYPE_ARTICLE_VN_EXPRESS
import com.example.rssreader.utils.ItemClickListener
import com.example.rssreader.utils.ItemContextMenuClickListener
import kotlinx.android.synthetic.main.adapter_article.view.*

class ArticleAdapter(
    private val itemClickListener: ItemClickListener,
    private val itemMenuClickListener: ItemContextMenuClickListener
) : ListAdapter<RecyclerViewItem, BaseViewHolder<RecyclerViewItem>>(ArticleDiffCallBack()) {

    class ArticleDiffCallBack : DiffUtil.ItemCallback<RecyclerViewItem>() {
        override fun areItemsTheSame(oldItem: RecyclerViewItem, newItem: RecyclerViewItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RecyclerViewItem, newItem: RecyclerViewItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<RecyclerViewItem> {
        return when (viewType) {
            TYPE_ARTICLE_VN_EXPRESS -> {
                ArticleViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.adapter_article, parent, false),
                    itemClickListener,
                    itemMenuClickListener
                )
            }
            TYPE_ARTICLE_24H -> {
                Article24hViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.adapter_article, parent, false),
                    itemClickListener,
                    itemMenuClickListener
                )
            }
            else -> {
                ArticleViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.adapter_article, parent, false),
                    itemClickListener,
                    itemMenuClickListener
                )
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<RecyclerViewItem>, position: Int) {
        return holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    class ArticleViewHolder(
        itemView: View, private val itemClickListener: ItemClickListener,
        val itemMenuClickListener: ItemContextMenuClickListener
    ) : BaseViewHolder<RecyclerViewItem>(itemView), View.OnCreateContextMenuListener {
        private var article: Article? = null

        init {
            itemView.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            val save: MenuItem = menu.add(Menu.NONE, 1, 1, "Save")
            val cancel: MenuItem = menu.add(Menu.NONE, 2, 2, "Cancel")
            save.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem?): Boolean {
                    Log.d("CONTEXT_MENU", "save ${article?.link}")
                    article?.let { itemMenuClickListener.onItemContextMenuClick(it) }
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

        override fun bind(item: RecyclerViewItem) {
            super.bind(item)
            this.article = item as? Article
            itemView.textViewTitle.text = article?.title
            itemView.textViewPubDate.text = article?.pubDate
            itemView.setOnClickListener {
                article?.let {
                    itemClickListener.onItemClicked(it)
                }
            }
        }
    }

    class Article24hViewHolder(
        itemView: View,
        private val itemClickListener: ItemClickListener,
        val itemMenuClickListener: ItemContextMenuClickListener
    ) :
        BaseViewHolder<RecyclerViewItem>(itemView), View.OnCreateContextMenuListener {
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

        override fun bind(item: RecyclerViewItem) {
            super.bind(item)
            this.article = item as Article
            itemView.textViewTitle.text = article.title
            itemView.textViewPubDate.text = article.pubDate
            itemView.setOnClickListener {
                article.let {
                    itemClickListener.onItemClicked(it)
                }
            }
        }
    }
}
