package com.example.android.lab11.quiz_application.data.quiz

import com.example.android.lab11.quiz_application.data.common.di.NetworkModule
import com.example.android.lab11.quiz_application.data.quiz.remote.api.QuizApi
import com.example.android.lab11.quiz_application.data.quiz.repository.QuizRepositoryImpl
import com.example.android.lab11.quiz_application.domain.quiz.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.Retrofit
import javax.inject.Singleton

@ExperimentalSerializationApi
@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class QuizModule {

    @Singleton
    @Provides
    fun provideQuizApi(retrofit: Retrofit): QuizApi = retrofit.create(QuizApi::class.java)

    @Singleton
    @Provides
    fun provideQuizRepository(quizApi: QuizApi): QuizRepository = QuizRepositoryImpl(quizApi)
}