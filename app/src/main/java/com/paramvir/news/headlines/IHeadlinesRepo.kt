package com.paramvir.news.headlines

import retrofit2.Response

interface IHeadlinesRepo {
    suspend fun getHeadlines(sources: String): Response<HeadlinesResponse>

}
