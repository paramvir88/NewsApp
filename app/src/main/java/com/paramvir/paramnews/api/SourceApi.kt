package com.paramvir.paramnews.api

import retrofit2.Response
import retrofit2.http.GET

interface SourceApi {
    @GET("v2/top-headlines/sources")
    suspend fun getAllNewsSources(): Response<SourcesResponse>
}