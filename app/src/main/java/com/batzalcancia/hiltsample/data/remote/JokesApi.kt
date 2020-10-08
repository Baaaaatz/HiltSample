package com.batzalcancia.hiltsample.data.remote

import com.batzalcancia.hiltsample.domain.entities.Joke
import retrofit2.http.GET

interface JokesApi  {

    @GET("any")
    suspend fun getRandomJoke(): Joke

}