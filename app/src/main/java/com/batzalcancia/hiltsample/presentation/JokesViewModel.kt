package com.batzalcancia.hiltsample.presentation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batzalcancia.hiltsample.domain.entities.Joke
import com.batzalcancia.hiltsample.domain.usecases.GetRandomJoke
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class JokesViewModel
@ViewModelInject
constructor(
    private val getRandomJoke: GetRandomJoke,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val jokesState = MutableStateFlow<UiState>(UiState.Loading)
    val joke = MutableStateFlow<Joke?>(null)


    init {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            jokesState.value = UiState.Error(throwable)
        }) {
            jokesState.value = UiState.Loading
            joke.value = getRandomJoke()
            println("HAHAHAAHA+${joke.value}")
            jokesState.value = UiState.Complete
        }
    }

}


sealed class UiState {
    object Loading: UiState()
    object Complete: UiState()
    class Error(val throwable: Throwable) : UiState()

}
