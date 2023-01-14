package com.example.android.lab11.quiz_application.presentation.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.lab11.quiz_application.domain.result.usecase.SaveBestResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(private val saveBestResultUseCase: SaveBestResultUseCase) :
    ViewModel() {

    fun saveBestResult(result: Int) {
        viewModelScope.launch {
            saveBestResultUseCase.execute(result)
        }
    }

}