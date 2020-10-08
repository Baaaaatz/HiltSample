package com.batzalcancia.hiltsample.utils


sealed class UiState {
    object Loading: UiState()
    object Complete: UiState()
    class Error(val throwable: Throwable) : UiState()

}