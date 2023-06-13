package com.example.android.lab11.quiz_application.domain.quiz.usecase

import com.example.android.lab11.quiz_application.domain.quiz.QuizRepository
import com.example.android.lab11.quiz_application.domain.quiz.entity.QuestionDomain
import javax.inject.Inject

class GetQuizQuestionsUseCase @Inject constructor(private val quizRepository: QuizRepository) {

    suspend fun execute(): Result<List<QuestionDomain>> = quizRepository.getQuizQuestions()
}