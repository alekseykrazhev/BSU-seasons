package com.example.android.lab11.quiz_application.data.theme

import com.example.android.lab11.quiz_application.data.common.PreferenceManager
import com.example.android.lab11.quiz_application.data.common.ResourceManager
import com.example.android.lab11.quiz_application.data.common.di.CommonModule
import com.example.android.lab11.quiz_application.data.theme.repository.AppThemeRepositoryImpl
import com.example.android.lab11.quiz_application.domain.main.AppThemeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [CommonModule::class])
@InstallIn(SingletonComponent::class)
class ThemeModule {

    @Singleton
    @Provides
    fun provideAppThemeRepository(
        prefsManager: PreferenceManager,
        resourceManager: ResourceManager
    ): AppThemeRepository =
        AppThemeRepositoryImpl(prefsManager, resourceManager)
}