package com.batzalcancia.hiltsample.domain.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Flags(
    val nsfw: Boolean,
    val political: Boolean,
    val racist: Boolean,
    val religious: Boolean,
    val sexist: Boolean
)