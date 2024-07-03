package com.paramvir.news.sources

import com.paramvir.news.api.SourcesResponse
import retrofit2.Response

interface ISourceRepo {
    suspend fun getAllSources(): Response<SourcesResponse>
}