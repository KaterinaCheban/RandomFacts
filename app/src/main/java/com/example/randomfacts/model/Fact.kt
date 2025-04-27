package com.example.randomfacts.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Fact(
    @SerialName("text") val text: String
)
