package com.example.android.lab11.quiz_application.data.common.mappers

interface Mapper<F, T> {

    fun map(input: F): T
}