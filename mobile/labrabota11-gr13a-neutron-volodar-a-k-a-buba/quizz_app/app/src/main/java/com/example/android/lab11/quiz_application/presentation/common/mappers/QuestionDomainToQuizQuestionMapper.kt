package com.example.android.lab11.quiz_application.presentation.common.mappers

import com.example.android.lab11.quiz_application.data.common.mappers.Mapper
import com.example.android.lab11.quiz_application.domain.quiz.entity.QuestionDomain
import com.example.android.lab11.quiz_application.presentation.common.extensions.fromHtml
import com.example.android.lab11.quiz_application.presentation.quiz.entity.Question

object QuestionDomainToQuizQuestionMapper : Mapper<QuestionDomain, Question> {

    override fun map(input: QuestionDomain): Question =
        with(input) {
            Question(
                question = question.fromHtml(),
                correctAnswer = correctAnswer.fromHtml(),
                allAnswers = getAllAnswers(this)
            )
        }

    private fun getAllAnswers(questionDomain: QuestionDomain): List<String> =
        with(questionDomain) {
            (incorrectAnswers as ArrayList).apply {
                add(correctAnswer)
                shuffle()
            }.map { it.fromHtml() }
        }
}