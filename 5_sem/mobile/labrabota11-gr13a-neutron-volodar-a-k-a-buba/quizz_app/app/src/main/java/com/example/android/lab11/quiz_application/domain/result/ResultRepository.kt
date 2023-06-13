package com.example.android.lab11.quiz_application.domain.result

interface ResultRepository {

    suspend fun getBestResult(): Int

    suspend fun saveBestResult(result: Int)

    // some comment
}