package com.batzalcancia.hiltsample.utils

import com.batzalcancia.hiltsample.domain.entities.Joke
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class JokesResponse(
    val error: Boolean,
    val jokes: List<Joke>
)