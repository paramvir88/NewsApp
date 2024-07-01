package com.paramvir.paramnews.headlines

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class NewsRepository constructor(private val newsDao: NewsDao) {
    val savedNews: Flow<List<News>> = newsDao.getAll()

    @WorkerThread
    fun insert(news: News) {
        newsDao.insertNews(news)
    }

    @WorkerThread
    fun delete(news: News) {
        newsDao.delete(news)
    }
}