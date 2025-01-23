package com.shashank.seekhoassignment.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shashank.common.utils.NetworkResult
import com.shashank.domain.domainModel.response.top_anime.TopAnime
import com.shashank.domain.usecase.TopAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopAnimeViewModel @Inject constructor(private val topAnimeUseCase: TopAnimeUseCase) :
    ViewModel() {

    private val _topAnimeList = MutableStateFlow<TopAnime?>(null)
    val topAnimeList: StateFlow<TopAnime?> get() = _topAnimeList

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    fun getTopAnimeList() = viewModelScope.launch {
        _loading.value = true
        when (val result = topAnimeUseCase.invoke()) {
            is NetworkResult.Success -> {
                _loading.value = false
                _topAnimeList.value = result.data
                Log.d("TopAnimeViewModel", "getTopAnimeList: ${result.data}")
            }

            is NetworkResult.Error -> {
                _loading.value = false
                _topAnimeList.value = null
                Log.d("TopAnimeViewModel", "getTopAnimeList: ${result.message}")
            }

            is NetworkResult.Loading -> {
                _loading.value = false
                _topAnimeList.value = null
            }
        }
    }
}