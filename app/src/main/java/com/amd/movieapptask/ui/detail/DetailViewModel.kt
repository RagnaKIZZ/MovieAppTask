package com.amd.movieapptask.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amd.movieapptask.data.database.model.MovieEntity
import com.amd.movieapptask.data.repository.Repository
import kotlinx.coroutines.*

class DetailViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _stateFavorite: MutableLiveData<Boolean> = MutableLiveData()
    val stateFavorie: LiveData<Boolean> get() = _stateFavorite

    fun getItemFavorite(movieEntity: MovieEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            val checkEntity = async { repository.getItemFavorite(movieEntity) }
            if (checkEntity.await().isNotEmpty()) {
                withContext(Dispatchers.Main) {
                    _stateFavorite.postValue(true)
                }
            } else {
                withContext(Dispatchers.Main) {
                    _stateFavorite.postValue(false)
                }
            }
        }
    }

    fun clickFavorite(movieEntity: MovieEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            val checkEntity = async { repository.getItemFavorite(movieEntity) }
            if (checkEntity.await().isEmpty()) {
                repository.addFavorite(movieEntity)
                withContext(Dispatchers.Main) {
                    _stateFavorite.postValue(true)
                }
            } else {
                repository.deleteFavorite(movieEntity)
                withContext(Dispatchers.Main) {
                    _stateFavorite.postValue(false)
                }
            }
        }
    }

}