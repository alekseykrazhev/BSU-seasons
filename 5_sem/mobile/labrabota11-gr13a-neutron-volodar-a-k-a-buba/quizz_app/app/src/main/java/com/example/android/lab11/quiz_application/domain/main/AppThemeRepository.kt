package com.example.android.lab11.quiz_application.domain.main

interface AppThemeRepository {
    suspend fun getAppTheme(): String
}