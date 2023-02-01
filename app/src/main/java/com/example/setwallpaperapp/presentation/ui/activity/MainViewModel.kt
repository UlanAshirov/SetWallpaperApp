package com.example.setwallpaperapp.presentation.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.setwallpaperapp.domain.usecases.GetImageUseCase
import com.example.setwallpaperapp.presentation.model.HitUI
import com.example.setwallpaperapp.presentation.model.toUI
import com.example.setwallpaperapp.presentation.utils.PixabayState
import com.example.setwallpaperapp.presentation.utils.networkstate.ListenNetwork
import com.example.setwallpaperapp.resourse.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val getImageUseCase: GetImageUseCase,
    listenNetwork: ListenNetwork
) : ViewModel() {

    private val _state = MutableStateFlow<PixabayState<List<HitUI>>>(PixabayState.Loading())
    val state = _state.asStateFlow()
    val isConnected: Flow<Boolean> = listenNetwork.isConnected

    fun getImage(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getImageUseCase(category).collect { hitModel ->
                when (hitModel) {
                    is Resource.Success -> {
                        _state.value = PixabayState.Success(hitModel.data!!.map { it.toUI() })
                    }
                    is Resource.Loading -> {
                        _state.value = PixabayState.Loading()
                    }
                    is Resource.Error -> {
                        _state.value =
                            PixabayState.Error(hitModel.message ?: "Unknown error")
                    }
                }
            }
        }
    }
}
