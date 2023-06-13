package com.example.android.lab11.quiz_application.domain.quiz.entity

import org.junit.Assert
import org.junit.Test

class QuestionDomainTest {
    val questionDomain = QuestionDomain("str1", "str2",  listOf("a", "b"))

    @Test
    fun checkData(){
        Assert.assertEquals(questionDomain.question, "str1")
        Assert.assertEquals(questionDomain.correctAnswer, "str2")
        Assert.assertEquals(questionDomain.incorrectAnswers, listOf("a", "b"))
    }
}