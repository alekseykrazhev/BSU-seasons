package com.example.android.lab11.quiz_application.domain.quiz.entity

import com.example.android.lab11.quiz_application.presentation.quiz.entity.Question
import com.example.android.lab11.quiz_application.presentation.quiz.entity.Quiz
import org.junit.Assert
import org.junit.Test

class QuizTest {
    val question1 = Question("question name 1", listOf("a", "b", "c", "d"), "a", "c")
    val question2 = Question("question name 2", listOf("a", "b", "c", "d"), "b", "b")
    val question3 = Question("question name 3", listOf("a", "b", "c", "d"), "c", "a")
    val quiz = Quiz(listOf(question1, question2, question3))

    @Test
    fun checkData(){
        Assert.assertEquals(quiz.questions, listOf(question1, question2, question3))
    }

    @Test
    fun checkMethods(){
        Assert.assertEquals(quiz.calculateScore(), 1)
        Assert.assertEquals(quiz.getCheckedProgress(), 3)
    }
}