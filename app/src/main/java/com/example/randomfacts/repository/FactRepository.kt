package com.example.randomfacts.repository

import com.example.randomfacts.network.FactApiService
import javax.inject.Inject

class FactRepository @Inject constructor(
    private val api: FactApiService
) {
    suspend fun getRandomFact() = api.getRandomFact()
}
