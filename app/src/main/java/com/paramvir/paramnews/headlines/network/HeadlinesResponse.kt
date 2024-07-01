package com.paramvir.paramnews.headlines.network

import com.paramvir.paramnews.headlines.network.Article


data class HeadlinesResponse(

    val articles: List<Article>,
    val status: String,
    val totalResults: Int


)
