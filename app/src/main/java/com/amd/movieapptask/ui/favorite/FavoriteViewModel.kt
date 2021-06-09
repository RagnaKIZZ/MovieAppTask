package com.amd.movieapptask.ui.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.amd.movieapptask.data.database.model.MovieEntity
import com.amd.movieapptask.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel @ViewModelInject constructor(
    private val repository : Repository
) : ViewModel() {

    fun getListFavorite() = repository.getListFavorite()

    fun deleteFavorite(movieEntity: MovieEntity) {
        CoroutineScope(Dispatchers.IO).launch{
            repository.deleteFavorite(movieEntity)
        }
    }


}