package com.batzalcancia.hiltsample.domain.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Joke(
    val category: String,
    val error: Boolean,
    val flags: Flags,
    val id: Int,
    val joke: String,
    val lang: String,
    val type: String
)