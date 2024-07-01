package com.paramvir.paramnews.headlines

import kotlinx.coroutines.flow.Flow

interface NewsDao {
    fun getAll(): Flow<List<News>>

    fun insertNews(news: News)

    fun delete(news: News)
}