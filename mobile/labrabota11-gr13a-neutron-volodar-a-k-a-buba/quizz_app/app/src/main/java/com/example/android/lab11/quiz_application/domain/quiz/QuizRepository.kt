package com.example.android.lab11.quiz_application.domain.quiz

import com.example.android.lab11.quiz_application.domain.quiz.entity.QuestionDomain

interface QuizRepository {

    suspend fun getQuizQuestions(): Result<List<QuestionDomain>>
}
