package com.paramvir.news.api

import com.google.gson.annotations.SerializedName

data class TopHeadlinesResponse(
    @SerializedName("status")
    val status: String = "",

    @SerializedName("totalResults")
    val totalResults: Int = 0,

    @SerializedName("articles")
    val articles: List<ApiArticle> = ArrayList()
)
