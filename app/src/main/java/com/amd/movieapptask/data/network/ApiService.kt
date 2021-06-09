package com.amd.movieapptask.data.network

import com.amd.movieapptask.data.model.ResponseMovie
import retrofit2.http.GET

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovie() : ResponseMovie

}