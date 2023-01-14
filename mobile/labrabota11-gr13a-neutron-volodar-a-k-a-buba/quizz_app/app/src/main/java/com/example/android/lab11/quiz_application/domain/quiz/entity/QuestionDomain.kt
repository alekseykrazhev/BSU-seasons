package com.example.android.lab11.quiz_application.domain.quiz.entity

data class QuestionDomain(
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>
)
