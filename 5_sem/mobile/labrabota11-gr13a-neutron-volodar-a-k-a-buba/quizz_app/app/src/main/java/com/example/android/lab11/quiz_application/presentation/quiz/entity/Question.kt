package com.example.android.lab11.quiz_application.presentation.quiz.entity

data class Question(
    val question: String,
    val allAnswers: List<String>,
    val correctAnswer: String,
    val checkedAnswer: String = ""
) {

    fun isCorrect(): Boolean = (correctAnswer == checkedAnswer)
    fun isChecked(): Boolean = checkedAnswer.isNotEmpty()
}
