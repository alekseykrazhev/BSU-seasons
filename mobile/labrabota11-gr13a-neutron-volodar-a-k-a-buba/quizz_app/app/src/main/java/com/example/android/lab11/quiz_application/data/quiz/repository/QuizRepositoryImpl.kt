package com.example.android.lab11.quiz_application.data.quiz.repository

import com.example.android.lab11.quiz_application.data.common.mappers.ResponseToQuestionDomainMapper
import com.example.android.lab11.quiz_application.data.quiz.remote.api.QuizApi
import com.example.android.lab11.quiz_application.domain.quiz.QuizRepository
import com.example.android.lab11.quiz_application.domain.quiz.entity.QuestionDomain
import javax.inject.Inject

private const val SUCCESS_CODE = 0

class QuizRepositoryImpl @Inject constructor(private val quizApi: QuizApi) : QuizRepository {

    override suspend fun getQuizQuestions(): Result<List<QuestionDomain>> =
        runCatching {
            with(quizApi.getQuestions()) {
                when (responseCode) {
                    SUCCESS_CODE -> questionResponses.map { ResponseToQuestionDomainMapper.map(it) }
                    else -> throw Throwable("Server error")
                }
            }
        }
}