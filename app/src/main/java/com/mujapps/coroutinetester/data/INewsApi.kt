package com.mujapps.coroutinetester.data

import com.mujapps.coroutinetester.model.NewsArticle
import retrofit2.http.GET

interface INewsApi {

    @GET("news.json")
    suspend fun getAllNews(): List<NewsArticle>
}