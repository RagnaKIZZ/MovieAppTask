package com.amd.movieapptask.data.repository

import androidx.lifecycle.LiveData
import com.amd.movieapptask.data.database.model.MovieEntity
import com.amd.movieapptask.data.model.ResponseMovie

interface Repository {

    suspend fun getPopularMovie() : ResponseMovie

    fun getListFavorite() : LiveData<List<MovieEntity>>

    fun getItemFavorite(moveEntity: MovieEntity) : List<MovieEntity>

    suspend fun addFavorite(moveEntity: MovieEntity) : Long

    suspend fun deleteFavorite(movieEntity: MovieEntity)


}