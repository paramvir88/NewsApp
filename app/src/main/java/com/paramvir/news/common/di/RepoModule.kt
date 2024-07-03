package com.paramvir.news.common.di

import com.paramvir.news.headlines.HeadlinesRepo
import com.paramvir.news.headlines.IHeadlinesRepo
import com.paramvir.news.sources.ISourceRepo
import com.paramvir.news.sources.SourceRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun headlinesRepo(headlinesRepo: HeadlinesRepo): IHeadlinesRepo

    @Binds
    abstract fun sourcesRepo(sourceRepo: SourceRepo): ISourceRepo
}