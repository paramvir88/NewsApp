package com.paramvir.news.headlines

import com.paramvir.news.headlines.HeadlinesResponse
import retrofit2.Response

interface IHeadlinesRepo {
    suspend fun getHeadlines(sources: String): Response<HeadlinesResponse>

}
