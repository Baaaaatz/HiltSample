package com.batzalcancia.hiltsample.presentation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.batzalcancia.hiltsample.data.paging.JokesPaging
import com.batzalcancia.hiltsample.domain.entities.Joke
import com.batzalcancia.hiltsample.domain.usecases.GetJokes
import com.batzalcancia.hiltsample.domain.usecases.GetRandomJoke
import com.batzalcancia.hiltsample.utils.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class JokesViewModel
@ViewModelInject
constructor(
    private val getRandomJoke: GetRandomJoke,
    private val getJokes: GetJokes,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val jokesState = MutableStateFlow<UiState>(UiState.Loading)
    val joke = MutableStateFlow<Joke?>(null)

    private val config = PagingConfig(
        pageSize = 5,
        enablePlaceholders = false,
        initialLoadSize = 5
    )
    val pagingData = Pager(
        config = config,
        initialKey = Pair(0, 5)
    ) {
        JokesPaging(getJokes)
    }.flow

    init {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            jokesState.value = UiState.Error(throwable)
        }) {
            jokesState.value = UiState.Loading
            joke.value = getRandomJoke()
            jokesState.value = UiState.Complete
        }
    }

}