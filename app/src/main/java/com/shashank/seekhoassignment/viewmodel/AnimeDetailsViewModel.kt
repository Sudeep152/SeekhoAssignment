package com.shashank.seekhoassignment.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shashank.common.utils.NetworkResult
import com.shashank.domain.domainModel.response.anime_detail.AnimeDetails
import com.shashank.domain.usecase.AnimeDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeDetailsViewModel @Inject constructor(private val animeDetailsUseCase: AnimeDetailsUseCase) : ViewModel() {

    private val _animeDetail = MutableStateFlow<AnimeDetails?>(null)
    val animeDetail: StateFlow<AnimeDetails?> get() = _animeDetail

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    fun getAnimeDetail(id: Int) = viewModelScope.launch {
        _loading.value = true
        when (val result = animeDetailsUseCase.invoke(id)) {
            is NetworkResult.Success -> {
                _loading.value = false
                _animeDetail.value = result.data
                Log.d("TopAnimeViewModel", "getAnimeDetail: ${result.data}")
            }

            is NetworkResult.Error -> {
                _loading.value = false
                _animeDetail.value = null
                Log.d("TopAnimeViewModel", "getAnimeDetail: ${result.message}")
            }

            is NetworkResult.Loading -> {
                _loading.value = false
                _animeDetail.value = null
            }
        }
    }
}