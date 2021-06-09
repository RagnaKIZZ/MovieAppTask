package com.amd.movieapptask.data.repository.remote

import androidx.lifecycle.LiveData
import com.amd.movieapptask.data.database.model.MovieEntity
import com.amd.movieapptask.data.model.ResponseMovie
import com.amd.movieapptask.data.network.ApiService
import com.amd.movieapptask.data.repository.Repository
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val apiService: ApiService
) : Repository {

    override suspend fun getPopularMovie(): ResponseMovie = apiService.getPopularMovie()
    override fun getItemFavorite(moveEntity: MovieEntity): List<MovieEntity> {
        throw UnsupportedOperationException()
    }

    override fun getListFavorite(): LiveData<List<MovieEntity>> {
        throw UnsupportedOperationException()
    }

    override suspend fun addFavorite(moveEntity: MovieEntity): Long {
        throw UnsupportedOperationException()
    }

    override suspend fun deleteFavorite(movieEntity: MovieEntity) {
        throw UnsupportedOperationException()
    }
}