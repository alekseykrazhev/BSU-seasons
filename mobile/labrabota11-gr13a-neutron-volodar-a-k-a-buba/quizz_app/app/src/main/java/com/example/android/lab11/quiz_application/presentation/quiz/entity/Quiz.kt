package com.example.android.lab11.quiz_application.presentation.quiz.entity

data class Quiz(
    val questions: List<Question> = emptyList()
) {

    fun calculateScore(): Int =
        questions.filter { question -> question.isCorrect() }.size

    fun getCheckedProgress(): Int =
        questions.filter { question -> question.isChecked() }.size
}