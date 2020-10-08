package com.batzalcancia.hiltsample.domain.repositories

import com.batzalcancia.hiltsample.domain.entities.Joke
import com.batzalcancia.hiltsample.utils.JokesResponse

interface JokesRepository {
    suspend fun getRandomJoke(): Joke
    suspend fun getJokes(idRange: String): JokesResponse
}