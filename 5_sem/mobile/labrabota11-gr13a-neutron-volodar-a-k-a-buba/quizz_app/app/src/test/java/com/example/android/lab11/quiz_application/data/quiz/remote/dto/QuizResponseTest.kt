package com.example.android.lab11.quiz_application.data.quiz.remote.dto

import org.junit.Assert
import org.junit.Test

class QuizResponseTest {
    val questionResponse1 = QuestionResponse(
        "history",
        "b",
        "hard",
        listOf("a", "c", "d"),
        "question text",
        "type"
    )
    val questionResponse2 = QuestionResponse(
        "cinema",
        "a",
        "medium",
        listOf("b", "c", "d"),
        "question text",
        "type"
    )
    val quizResponse = QuizResponse(123, listOf(questionResponse1, questionResponse2))

    @Test
    fun checkData(){
        Assert.assertEquals(quizResponse.responseCode, 123)
        Assert.assertEquals(quizResponse.questionResponses, listOf(questionResponse1, questionResponse2))
    }
}