package com.example.rssreader.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rssreader.R
import com.example.rssreader.data.source.model.Article
import com.example.rssreader.utils.ItemClickListener
import kotlinx.android.synthetic.main.adapter_article.view.*

class ArticleAdapter(
    private val itemClickListener: ItemClickListener
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
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Article, itemClickListener: ItemClickListener) {
            itemView.textViewTitle.text = article.title
            itemView.textViewPubDate.text = article.pubDate

            itemView.setOnClickListener {
                itemClickListener.onItemClicked(article)
            }
        }
    }
}
