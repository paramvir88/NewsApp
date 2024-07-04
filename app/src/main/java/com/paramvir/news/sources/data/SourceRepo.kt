package com.paramvir.news.sources.data

import retrofit2.Response
import javax.inject.Inject

class SourceRepo @Inject constructor(private val api: SourceApi) : ISourceRepo {
    override suspend fun getAllSources(): Response<SourcesResponse> {
        return api.getAllNewsSources()
    }
}