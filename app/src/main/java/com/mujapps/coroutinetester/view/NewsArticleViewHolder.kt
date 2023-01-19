package com.mujapps.coroutinetester.view

import androidx.recyclerview.widget.RecyclerView
import com.mujapps.coroutinetester.databinding.ItemNewsArticleBinding
import com.mujapps.coroutinetester.model.NewsArticle

class NewsArticleViewHolder(var mNewsItemBinding: ItemNewsArticleBinding) :
    RecyclerView.ViewHolder(mNewsItemBinding.root) {

    fun onBindData(newsArticle: NewsArticle) {
        mNewsItemBinding.mLblNewsTitle.text = newsArticle.title
    }
}