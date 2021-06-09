package com.amd.movieapptask.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.amd.movieapptask.data.database.model.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(movieEntity: MovieEntity) : Long

    @Query("SELECT * FROM movie")
    fun getList() : LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getItem(id: Int) : List<MovieEntity>

    @Delete
    fun delete(movieEntity : MovieEntity)

}