package com.paramvir.news.sources

import com.paramvir.news.api.SourcesResponse
import com.paramvir.news.sources.data.ISourceRepo
import retrofit2.Response
import javax.inject.Inject

class SourceRepo @Inject constructor(private val api: SourceApi) : ISourceRepo {
    override suspend fun getAllSources(): Response<SourcesResponse> {
        return api.getAllNewsSources()
    }
}