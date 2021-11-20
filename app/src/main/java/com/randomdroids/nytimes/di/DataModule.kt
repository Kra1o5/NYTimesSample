package com.randomdroids.nytimes.di

import com.randomdroids.nytimes.data.repository.NYTimesRepository
import com.randomdroids.nytimes.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun nyTimesRepositoryProvider(
        remoteDataSource: RemoteDataSource
    ) = NYTimesRepository(remoteDataSource)
}