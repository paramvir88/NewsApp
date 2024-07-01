package com.android.newsapp.sources.repo

import com.paramvir.paramnews.api.SourcesResponse
import retrofit2.Response

interface ISourceRepo {
    suspend fun getAllSources(): Response<SourcesResponse>
}