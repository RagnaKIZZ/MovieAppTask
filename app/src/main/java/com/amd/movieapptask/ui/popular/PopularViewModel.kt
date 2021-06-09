package com.amd.movieapptask.ui.popular

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amd.movieapptask.data.model.ResponseMovie
import com.amd.movieapptask.data.repository.Repository
import com.amd.movieapptask.utils.Resource
import com.amd.movieapptask.utils.Utils
import kotlinx.coroutines.launch

class PopularViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _popularMovie: MutableLiveData<Resource<ResponseMovie>> = MutableLiveData()
    val popularMovie: LiveData<Resource<ResponseMovie>>
        get() = _popularMovie

    init {
        getPopularMovie()
    }

    fun getPopularMovie() = viewModelScope.launch {
        _popularMovie.value = Resource.Loading
        _popularMovie.value = Utils.safeApiCall {
            repository.getPopularMovie()
        }
    }

}