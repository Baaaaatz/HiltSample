package com.batzalcancia.hiltsample.domain.usecases

import com.batzalcancia.hiltsample.domain.entities.Joke
import com.batzalcancia.hiltsample.domain.repositories.JokesRepository
import javax.inject.Inject

class GetRandomJoke @Inject constructor(private val jokesRepository: JokesRepository) {
    suspend operator fun invoke(): Joke {
        return jokesRepository.getRandomJoke()
    }
}