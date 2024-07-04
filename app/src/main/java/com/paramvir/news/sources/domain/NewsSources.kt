package com.paramvir.news.sources.domain

data class NewsSources(
    val id: String,
    val name: String,
    val isSelected: Boolean = false
)
