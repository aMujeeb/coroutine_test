package com.mujapps.coroutinetester.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mujapps.coroutinetester.data.NewsRepository

class NewsMainViewModel : ViewModel() {

    val newsArticles = NewsRepository().getNewsArticles().asLiveData()
}