package com.amd.movieapptask.di

import android.content.Context
import com.amd.movieapptask.data.database.LocalDatabase
import com.amd.movieapptask.data.network.ApiService
import com.amd.movieapptask.data.repository.local.LocalRepository
import com.amd.movieapptask.data.repository.remote.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context) = LocalDatabase(context)

    @Provides
    @Singleton
    fun provideRemoteRepository(apiService: ApiService) = RemoteRepository(apiService)

    @Provides
    @Singleton
    fun provideLocalRepository(localDatabase: LocalDatabase) = LocalRepository(localDatabase)

}