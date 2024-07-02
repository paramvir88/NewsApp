package com.paramvir.paramnews.sources.repo

import com.paramvir.paramnews.api.SourceApi
import com.paramvir.paramnews.api.SourcesResponse
import retrofit2.Response
import javax.inject.Inject

class SourceRepo @Inject constructor(private val api: SourceApi) : ISourceRepo {
    override suspend fun getAllSources(): Response<SourcesResponse> {
        return api.getAllNewsSources()
    }
}