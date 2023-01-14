package com.example.android.lab11.quiz_application.data.theme.repository

import com.example.android.lab11.quiz_application.R
import com.example.android.lab11.quiz_application.data.common.PreferenceManager
import com.example.android.lab11.quiz_application.data.common.ResourceManager
import com.example.android.lab11.quiz_application.domain.main.AppThemeRepository
import javax.inject.Inject

class AppThemeRepositoryImpl @Inject constructor(
    private val prefsManager: PreferenceManager,
    private val resourceManager: ResourceManager
) : AppThemeRepository {

    override suspend fun getAppTheme(): String {
        val key = resourceManager.getString(R.string.key_theme)
        return prefsManager.get(key, "")
    }
}