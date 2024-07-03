package com.paramvir.news.headlines.repo

import com.paramvir.news.headlines.network.HeadlinesResponse
import retrofit2.Response

interface IHeadlinesRepo {
    suspend fun getHeadlines(sources: String): Response<HeadlinesResponse>

}
