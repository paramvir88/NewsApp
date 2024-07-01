package com.paramvir.paramnews.di

import com.android.newsapp.sources.repo.ISourceRepo
import com.android.newsapp.sources.repo.SourceRepo
import com.paramvir.paramnews.headlines.repo.HeadlinesRepo
import com.paramvir.paramnews.headlines.repo.IHeadlinesRepo
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