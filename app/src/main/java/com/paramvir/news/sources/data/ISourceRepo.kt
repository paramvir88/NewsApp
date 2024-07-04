package com.paramvir.news.sources.data

import retrofit2.Response

interface ISourceRepo {
    suspend fun getAllSources(): Response<SourcesResponse>
}