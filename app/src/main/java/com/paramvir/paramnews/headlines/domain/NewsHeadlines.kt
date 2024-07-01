package com.paramvir.paramnews.headlines.domain

data class NewsHeadlines(
    val id: String = "",
    val title: String? = "",
    val description: String? = "",
    val author: String? = "",
    val pic: String? = "",
    val url: String? = ""
)
