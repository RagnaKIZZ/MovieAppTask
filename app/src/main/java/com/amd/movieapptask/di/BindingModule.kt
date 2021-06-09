package com.amd.movieapptask.di

import com.amd.movieapptask.data.repository.DataRepository
import com.amd.movieapptask.data.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class BindingModule {

    @Binds
    abstract fun bindRepository(data: DataRepository): Repository

}