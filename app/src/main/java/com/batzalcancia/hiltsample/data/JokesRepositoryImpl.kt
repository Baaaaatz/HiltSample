package com.batzalcancia.hiltsample.data

import com.batzalcancia.hiltsample.data.remote.JokesApi
import com.batzalcancia.hiltsample.domain.entities.Joke
import com.batzalcancia.hiltsample.domain.repositories.JokesRepository
import javax.inject.Inject

class JokesRepositoryImpl @Inject constructor(private val jokesApi: JokesApi) : JokesRepository {
    override suspend fun getRandomJoke(): Joke {
        return jokesApi.getRandomJoke()
    }
}