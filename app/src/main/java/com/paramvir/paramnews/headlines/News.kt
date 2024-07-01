package com.paramvir.paramnews.headlines

data class News(
    val id: String,
    val title: String = "",
    val description: String = "",
    val author: String = "",
    val pic: String = "",
    val url: String = ""
)
