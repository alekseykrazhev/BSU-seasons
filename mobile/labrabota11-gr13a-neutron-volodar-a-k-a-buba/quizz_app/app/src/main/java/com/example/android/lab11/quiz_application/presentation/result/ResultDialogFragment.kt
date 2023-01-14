package com.example.android.lab11.quiz_application.presentation.result

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android.lab11.quiz_application.R
import com.example.android.lab11.quiz_application.databinding.DialogQuizResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultDialogFragment : DialogFragment(R.layout.dialog_quiz_result) {

    private val binding by viewBinding(DialogQuizResultBinding::bind)

    private val arguments by navArgs<ResultDialogFragmentArgs>()

    private val viewModel: ResultViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dqrTvResult.text = resources.getString(R.string.quiz_result, arguments.result)

        saveResult()

        setListeners()
    }

    private fun saveResult() {
        viewModel.saveBestResult(arguments.result)
    }

    private fun setListeners() {
        binding.dqrBtnPositive.setOnClickListener {
            val navController = findNavController()
            navController.navigate(ResultDialogFragmentDirections.actionResultDestToStartScreenDest())
        }
        dialog?.setOnCancelListener {
            val navController = findNavController()
            navController.navigate(ResultDialogFragmentDirections.actionResultDestToStartScreenDest())
        }
    }

}