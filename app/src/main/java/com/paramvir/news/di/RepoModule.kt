package com.paramvir.news.di

import com.paramvir.news.sources.repo.ISourceRepo
import com.paramvir.news.sources.repo.SourceRepo
import com.paramvir.news.headlines.repo.HeadlinesRepo
import com.paramvir.news.headlines.repo.IHeadlinesRepo
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