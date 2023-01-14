package com.example.android.lab11.quiz_application.data.quiz.remote.api

import com.example.android.lab11.quiz_application.data.quiz.remote.dto.QuizResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApi {

    @GET("?")
    suspend fun getQuestions(
        @Query("amount") numberOfQuestions: Int = 10
    ): QuizResponse
}