package com.amd.movieapptask.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amd.movieapptask.BuildConfig
import com.amd.movieapptask.data.database.dao.MovieDao
import com.amd.movieapptask.data.database.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 5, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun movie() : MovieDao

    companion object {
        @Volatile private var instance : LocalDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            LocalDatabase::class.java,
            BuildConfig.APPLICATION_ID
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}