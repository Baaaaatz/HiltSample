package com.batzalcancia.hiltsample.presentation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.insertSeparators
import androidx.paging.map
import com.batzalcancia.hiltsample.data.paging.JokesPaging
import com.batzalcancia.hiltsample.domain.entities.Joke
import com.batzalcancia.hiltsample.domain.usecases.GetJokes
import com.batzalcancia.hiltsample.domain.usecases.GetRandomJoke
import com.batzalcancia.hiltsample.utils.UiModel
import com.batzalcancia.hiltsample.utils.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
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
        initialLoadSize = 30
    )

    val pagingData = Pager(
        config = config,
        initialKey = Pair(0, 5)
    ) {
        JokesPaging(getJokes)
    }.flow.map { pagingData ->
        pagingData.map { UiModel.Jokes(it) }
    }.map {
        it.insertSeparators { before, after ->
            if (after == null) return@insertSeparators null
            if (before == null) return@insertSeparators null

            if (before.joke.category != after.joke.category) UiModel.TypeSeparator(after.joke.category)
            else null
        }
    }

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