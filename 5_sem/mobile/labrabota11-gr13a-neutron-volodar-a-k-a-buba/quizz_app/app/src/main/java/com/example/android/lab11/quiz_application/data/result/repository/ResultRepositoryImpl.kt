package com.example.android.lab11.quiz_application.data.result.repository

import com.example.android.lab11.quiz_application.R
import com.example.android.lab11.quiz_application.data.common.PreferenceManager
import com.example.android.lab11.quiz_application.data.common.ResourceManager
import com.example.android.lab11.quiz_application.domain.result.ResultRepository
import javax.inject.Inject

class ResultRepositoryImpl @Inject constructor(
    private val prefsManager: PreferenceManager,
    private val resourceManager: ResourceManager
) : ResultRepository {

    override suspend fun getBestResult(): Int {
        val key = resourceManager.getString(R.string.key_result)
        return prefsManager.get(key,-1)
    }

    override suspend fun saveBestResult(result: Int) {
        if (getBestResult() < result) {
            val key = resourceManager.getString(R.string.key_result)
            prefsManager.put(key, result)
        }
    }
}