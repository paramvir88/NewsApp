package com.paramvir.news.headlines

import com.paramvir.news.headlines.Article


data class HeadlinesResponse(

    val articles: List<Article>,
    val status: String,
    val totalResults: Int


)
