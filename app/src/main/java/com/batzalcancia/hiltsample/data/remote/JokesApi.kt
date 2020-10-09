package com.batzalcancia.hiltsample.data.remote

import com.batzalcancia.hiltsample.domain.entities.Joke
import com.batzalcancia.hiltsample.utils.JokesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface JokesApi {

    @GET("Any")
    suspend fun getRandomJoke(): Joke

    @GET("Any")
    suspend fun getJokes(
        @Query("idRange") idRange: String,
        @Query("amount") amount: Int = 5,
        @Query("type") type: String = "single"
    ):  JokesResponse

}