package com.example.android.lab11.quiz_application.data.common.mappers

import com.example.android.lab11.quiz_application.data.quiz.remote.dto.QuestionResponse
import com.example.android.lab11.quiz_application.domain.quiz.entity.QuestionDomain

object ResponseToQuestionDomainMapper : Mapper<QuestionResponse, QuestionDomain> {

    override fun map(input: QuestionResponse): QuestionDomain =
        with(input) {
            QuestionDomain(
                question = question,
                correctAnswer = correctAnswer,
                incorrectAnswers = incorrectAnswers
            )
        }

}