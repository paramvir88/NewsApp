package com.paramvir.paramnews.headlines.repo

import com.paramvir.paramnews.headlines.network.HeadlinesResponse
import retrofit2.Response

interface IHeadlinesRepo {
    suspend fun getHeadlines(sources: String): Response<HeadlinesResponse>

}
