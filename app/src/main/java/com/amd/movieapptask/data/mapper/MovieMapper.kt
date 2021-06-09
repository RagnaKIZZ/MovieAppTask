package com.amd.movieapptask.data.mapper

import com.amd.movieapptask.data.database.model.MovieEntity
import com.amd.movieapptask.data.model.ResultsItem

object MovieMapper {

    fun mapEntityToResponse(movieEntity: MovieEntity) = ResultsItem(
        overview = movieEntity.overview,
        id = movieEntity.id,
        releaseDate = movieEntity.release_date,
        backdropPath = movieEntity.backdrop_path,
        title = movieEntity.title,
        voteAverage = movieEntity.vote_average,
        popularity = movieEntity.popularity,
        posterPath = movieEntity.poster_path,
        genreIds = null
    )

    fun mapResponseToEntity(movieItem: ResultsItem) = MovieEntity(
        id = movieItem.id!!,
        title = movieItem.title,
        overview = movieItem.overview,
        release_date = movieItem.releaseDate,
        backdrop_path = movieItem.backdropPath,
        poster_path = movieItem.posterPath,
        vote_average = movieItem.voteAverage,
        popularity = movieItem.popularity,
        genre = movieItem.genreIds?.get(0).toString()
    )

}