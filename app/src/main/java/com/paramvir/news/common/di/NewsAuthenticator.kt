package com.paramvir.news.common.di

import com.paramvir.news.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

private const val API_KEY = "X-Api-Key"

class NewsAuthenticator @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader(API_KEY, BuildConfig.API_KEY)
        return chain.proceed(builder.build())
    }

}
