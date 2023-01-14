package com.example.android.lab11.quiz_application.data.quiz.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionResponse(
    @SerialName("category")
    val category: String,
    @SerialName("correct_answer")
    val correctAnswer: String,
    @SerialName("difficulty")
    val difficulty: String,
    @SerialName("incorrect_answers")
    val incorrectAnswers: List<String>,
    @SerialName("question")
    val question: String,
    @SerialName("type")
    val type: String
)