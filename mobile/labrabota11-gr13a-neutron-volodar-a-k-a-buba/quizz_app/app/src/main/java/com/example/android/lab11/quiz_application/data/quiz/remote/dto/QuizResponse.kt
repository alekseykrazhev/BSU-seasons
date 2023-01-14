package com.example.android.lab11.quiz_application.data.quiz.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuizResponse(
    @SerialName("response_code")
    val responseCode: Int,
    @SerialName("results")
    val questionResponses: List<QuestionResponse>
)