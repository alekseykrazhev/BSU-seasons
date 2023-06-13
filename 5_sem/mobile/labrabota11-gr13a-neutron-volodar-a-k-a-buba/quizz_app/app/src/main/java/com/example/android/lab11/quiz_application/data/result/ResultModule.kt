package com.example.android.lab11.quiz_application.data.result

import com.example.android.lab11.quiz_application.data.common.PreferenceManager
import com.example.android.lab11.quiz_application.data.common.ResourceManager
import com.example.android.lab11.quiz_application.data.common.di.CommonModule
import com.example.android.lab11.quiz_application.data.result.repository.ResultRepositoryImpl
import com.example.android.lab11.quiz_application.domain.result.ResultRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [CommonModule::class])
@InstallIn(SingletonComponent::class)
class ResultModule {

    @Singleton
    @Provides
    fun provideResultRepository(
        prefsManager: PreferenceManager,
        resourceManager: ResourceManager

    ): ResultRepository =
        ResultRepositoryImpl(prefsManager, resourceManager)
}