package com.amd.movieapptask.data.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie")
data class MovieEntity(
    val backdrop_path : String?,
    @PrimaryKey
    val id : Int,
    val overview : String?,
    val poster_path : String?,
    val title : String?,
    val release_date : String?,
    val vote_average : Float?,
    val popularity : Double?,
    val genre : String?
) : Parcelable