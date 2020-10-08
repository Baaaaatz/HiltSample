package com.batzalcancia.hiltsample.data

import com.batzalcancia.hiltsample.data.remote.JokesApi
import com.batzalcancia.hiltsample.domain.entities.Joke
import com.batzalcancia.hiltsample.domain.repositories.JokesRepository
import com.batzalcancia.hiltsample.utils.JokesResponse
import javax.inject.Inject

class JokesRepositoryImpl @Inject constructor(private val jokesApi: JokesApi) : JokesRepository {

    override suspend fun getRandomJoke() = jokesApi.getRandomJoke()

    override suspend fun getJokes(idRange: String) = jokesApi.getJokes(idRange)

}