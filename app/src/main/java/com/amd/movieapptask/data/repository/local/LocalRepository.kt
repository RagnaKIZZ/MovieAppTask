package com.amd.movieapptask.data.repository.local

import androidx.lifecycle.LiveData
import com.amd.movieapptask.data.database.LocalDatabase
import com.amd.movieapptask.data.database.model.MovieEntity
import com.amd.movieapptask.data.model.ResponseMovie
import com.amd.movieapptask.data.repository.Repository

class LocalRepository(
    private val localDatabase: LocalDatabase
) : Repository{

    override suspend fun getPopularMovie(): ResponseMovie {
        throw UnsupportedOperationException()
    }

    override fun getItemFavorite(moveEntity: MovieEntity): List<MovieEntity> {
        return localDatabase.movie().getItem(moveEntity.id)
    }

    override fun getListFavorite(): LiveData<List<MovieEntity>> = localDatabase.movie().getList()

    override suspend fun addFavorite(moveEntity: MovieEntity) : Long {
        return localDatabase.movie().add(moveEntity)
    }

    override suspend fun deleteFavorite(movieEntity: MovieEntity) {
        localDatabase.movie().delete(movieEntity)
    }

}