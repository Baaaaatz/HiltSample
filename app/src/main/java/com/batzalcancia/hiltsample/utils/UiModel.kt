package com.batzalcancia.hiltsample.utils

import com.batzalcancia.hiltsample.domain.entities.Joke

sealed class UiModel {
    data class Jokes(val joke: Joke) : UiModel()
    data class  TypeSeparator(val category: String): UiModel()
}