package com.mujapps.coroutinetester.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mujapps.coroutinetester.databinding.ActivityNewsMainBinding

class NewsMainActivity : AppCompatActivity() {

    private val mNewsMainViewBinding: ActivityNewsMainBinding by lazy {
        ActivityNewsMainBinding.inflate(layoutInflater)
    }

    private val mNewsViewModel: NewsMainViewModel by viewModels()

    private lateinit var mNewsItemsAdapter: NewsArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mNewsMainViewBinding.root)

        mNewsItemsAdapter = NewsArticlesAdapter()
        mNewsMainViewBinding.mLstNewsArticles.layoutManager = LinearLayoutManager(this)
        mNewsMainViewBinding.mLstNewsArticles.adapter = mNewsItemsAdapter


        mNewsViewModel.newsArticles.observe(this) {
            mNewsMainViewBinding.mNewsProgress.visibility = View.GONE
            mNewsMainViewBinding.mLstNewsArticles.visibility = View.VISIBLE
            mNewsItemsAdapter.addNewNewsArticles(it)
            mNewsMainViewBinding.mLstNewsArticles.smoothScrollToPosition(0)
        }
    }
}