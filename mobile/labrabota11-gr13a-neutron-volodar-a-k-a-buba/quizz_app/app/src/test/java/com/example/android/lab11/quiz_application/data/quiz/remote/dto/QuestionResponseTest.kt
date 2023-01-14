package com.example.android.lab11.quiz_application.data.quiz.remote.dto

import org.junit.Assert
import org.junit.Test

class QuestionResponseTest {
    val questionResponse = QuestionResponse(
        "history",
        "b",
        "hard",
        listOf("a", "c", "d"),
        "question text",
        "type"
    )

    @Test
    fun checkData(){
        Assert.assertEquals(questionResponse.category, "history")
        Assert.assertEquals(questionResponse.correctAnswer, "b")
        Assert.assertEquals(questionResponse.difficulty, "hard")
        Assert.assertEquals(questionResponse.incorrectAnswers, listOf("a", "c", "d"))
        Assert.assertEquals(questionResponse.question, "question text")
        Assert.assertEquals(questionResponse.type, "type")
    }
}