package com.example.android.lab11.quiz_application.domain.main.usecase

import com.example.android.lab11.quiz_application.domain.main.AppThemeRepository
import org.jetbrains.annotations.TestOnly
import javax.inject.Inject

class GetAppThemeUseCase @Inject constructor(private val repository: AppThemeRepository) {

    suspend fun execute(): String = repository.getAppTheme()

}