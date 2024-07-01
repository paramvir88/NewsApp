package com.paramvir.paramnews.headlines.repo

import com.paramvir.paramnews.headlines.network.HeadlinesApi
import com.paramvir.paramnews.headlines.network.HeadlinesResponse
import retrofit2.Response
import javax.inject.Inject

class HeadlinesRepo @Inject constructor(private val api: HeadlinesApi) : IHeadlinesRepo {
    override suspend fun getHeadlines(sources: String): Response<HeadlinesResponse> {
        return api.getHeadlines(sources)
    }
}