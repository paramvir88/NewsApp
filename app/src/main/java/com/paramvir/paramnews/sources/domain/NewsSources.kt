package com.paramvir.paramnews.sources.domain

data class NewsSources(
    val id:String,
    val name:String,
    val isSelected:Boolean = false
)
