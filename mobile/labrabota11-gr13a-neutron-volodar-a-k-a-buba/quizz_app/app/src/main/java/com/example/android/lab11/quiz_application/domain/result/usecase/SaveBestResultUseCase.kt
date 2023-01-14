package com.example.android.lab11.quiz_application.domain.result.usecase

import com.example.android.lab11.quiz_application.domain.result.ResultRepository
import javax.inject.Inject

class SaveBestResultUseCase @Inject constructor(private val repository: ResultRepository) {

    suspend fun execute(result: Int) {
        repository.saveBestResult(result)
    }

}