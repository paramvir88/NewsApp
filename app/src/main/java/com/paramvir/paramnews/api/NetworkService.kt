package com.paramvir.paramnews.api

import com.paramvir.paramnews.utils.Constants.DEFAULT_COUNTRY
import retrofit2.http.GET
import retrofit2.http.Query



interface NetworkService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String = DEFAULT_COUNTRY): TopHeadlinesResponse
}