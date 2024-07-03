package com.paramvir.news.headlines


data class HeadlinesResponse(

    val articles: List<Article>,
    val status: String,
    val totalResults: Int


)
