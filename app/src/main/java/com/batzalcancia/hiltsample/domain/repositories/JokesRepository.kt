package com.batzalcancia.hiltsample.domain.repositories

import com.batzalcancia.hiltsample.domain.entities.Joke

interface JokesRepository {

    suspend fun getRandomJoke(): Joke

}