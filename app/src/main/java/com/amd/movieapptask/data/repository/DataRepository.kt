package com.amd.movieapptask.data.repository

import androidx.lifecycle.LiveData
import com.amd.movieapptask.data.database.model.MovieEntity
import com.amd.movieapptask.data.model.ResponseMovie
import com.amd.movieapptask.data.repository.local.LocalRepository
import com.amd.movieapptask.data.repository.remote.RemoteRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : Repository{

    override suspend fun getPopularMovie(): ResponseMovie = remoteRepository.getPopularMovie()

    override fun getListFavorite(): LiveData<List<MovieEntity>> = localRepository.getListFavorite()

    override suspend fun addFavorite(moveEntity: MovieEntity): Long = localRepository.addFavorite(moveEntity)

    override suspend fun deleteFavorite(movieEntity: MovieEntity) {
        localRepository.deleteFavorite(movieEntity)
    }

    override fun getItemFavorite(movieEntity: MovieEntity): List<MovieEntity> {
        return localRepository.getItemFavorite(movieEntity)
    }
}