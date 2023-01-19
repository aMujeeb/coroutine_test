package com.mujapps.coroutinetester.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mujapps.coroutinetester.databinding.ItemNewsArticleBinding
import com.mujapps.coroutinetester.model.NewsArticle

class NewsArticlesAdapter : RecyclerView.Adapter<NewsArticleViewHolder>() {

    private var mNewsItems = arrayListOf<NewsArticle>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsArticleViewHolder {
        val mNewsArticleViewBinding: ItemNewsArticleBinding = ItemNewsArticleBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NewsArticleViewHolder(mNewsArticleViewBinding)
    }

    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int) {
        holder.onBindData(mNewsItems[position])
    }

    fun addNewNewsArticles(item: NewsArticle) {
        mNewsItems.add(0, item)
        notifyItemInserted(0)
    }

    class NewsArticleDiff : DiffUtil.ItemCallback<NewsArticle>() {
        override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle) =
            oldItem == newItem
    }

    override fun getItemCount() = mNewsItems.size
}