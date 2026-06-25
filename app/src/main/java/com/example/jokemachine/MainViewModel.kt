package com.example.jokemachine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = JokeRepository()

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        loadRandomJoke()
    }

    fun loadRandomJoke() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val joke = repository.getRandomJoke()
                _uiState.value = UiState.Success(joke)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class UiState {
    object Loading : UiState()
    data class Success(val joke: Joke) : UiState()
    data class Error(val message: String) : UiState()
}