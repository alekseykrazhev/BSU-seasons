package com.example.android.lab11.quiz_application.presentation.quiz.entity

import org.junit.Assert
import org.junit.Test

class QuestionTest {

    val question = Question("question name", listOf("a", "b", "c"), "b", "b")

    // first test
    @Test
    fun checkData(){
        Assert.assertEquals(question.question, "question name")
        Assert.assertEquals(question.allAnswers, listOf("a", "b", "c"))
        Assert.assertEquals(question.checkedAnswer, "b")
        Assert.assertEquals(question.correctAnswer, "b")
    }

    // second test
    @Test
    fun checkMethods(){
        Assert.assertEquals(question.isCorrect(), true)
        Assert.assertEquals(question.isChecked(), true)
    }
}