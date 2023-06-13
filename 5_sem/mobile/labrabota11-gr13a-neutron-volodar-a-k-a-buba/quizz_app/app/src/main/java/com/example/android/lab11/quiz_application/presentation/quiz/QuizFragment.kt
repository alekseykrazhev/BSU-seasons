package com.example.android.lab11.quiz_application.presentation.quiz

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android.lab11.quiz_application.R
import com.example.android.lab11.quiz_application.databinding.FragmentQuizBinding
import com.example.android.lab11.quiz_application.presentation.quiz.adapter.QuizSubmitAdapter
import com.example.android.lab11.quiz_application.presentation.quiz.adapter.QuizViewPagerAdapter
import com.example.android.lab11.quiz_application.presentation.quiz.entity.Quiz
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class QuizFragment : Fragment(R.layout.fragment_quiz) {

    private val binding by viewBinding(FragmentQuizBinding::bind)

    private val viewModel: QuizViewModel by viewModels()

    private val quizAdapter: QuizViewPagerAdapter by lazy {
        QuizViewPagerAdapter { position, checkedAnswer ->
            handleAnswerCheck(position, checkedAnswer)
        }
    }

    private val submitAdapter: QuizSubmitAdapter by lazy {
        QuizSubmitAdapter {
            goToResult()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewPager()

        setObservers()

        setListeners()

        viewModel.getQuiz()
    }

    private fun setViewPager() {
        binding.fqViewPager.adapter = ConcatAdapter(quizAdapter, submitAdapter)
    }

    private fun setListeners() {
        with(binding) {
            fqBtnRetry.setOnClickListener {
                viewModel.getQuiz()
            }
            fqViewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    viewModel.updatePosition(position)
                }
            })
        }
    }

    private fun setObservers() {
        viewModel.viewState
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach {
                when (it) {
                    is QuizViewState.Loading -> {
                        handleLoadingState()
                    }
                    is QuizViewState.Success -> {
                        handleSuccessResult(it)
                    }
                    is QuizViewState.Error -> {
                        handleFailResult(it.errorMessage)
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleSuccessResult(viewState: QuizViewState.Success) {
        with(viewState) {
            updateQuiz(quiz)
            setCheckedProgress(checkedProgress)
            setProgress(progress)
            setPosition(currentPosition)
        }
        showLoadingUi(false)
        showErrorUi(false)
        showQuizUi(true)
    }

    private fun handleFailResult(error: String) {
        showLoadingUi(false)
        showQuizUi(false)
        showErrorUi(true, error)
    }

    private fun handleLoadingState() {
        showErrorUi(false)
        showQuizUi(false)
        showLoadingUi(true)
    }

    private fun handleAnswerCheck(position: Int, checkedAnswer: String) {
        viewModel.setCheckedAnswer(position, checkedAnswer)
    }

    private fun goToResult() {
        val result = viewModel.getQuizResult()
        findNavController().navigate(
            QuizFragmentDirections.actionQuizDestToResultDest(
                result
            )
        )
    }

    private fun showLoadingUi(isVisible: Boolean) {
        binding.fqPbLoadingProgress.isVisible = isVisible
    }

    private fun showQuizUi(isVisible: Boolean) {
        with(binding) {
            fqPbQuizProgress.isVisible = isVisible
            fqViewPager.isVisible = isVisible
        }
    }

    private fun showErrorUi(isVisible: Boolean, error: String = "") {
        with(binding) {
            fqTvErrorMessage.text = error
            fqBtnRetry.isVisible = isVisible
            fqTvErrorMessage.isVisible = isVisible
        }
    }

    private fun updateQuiz(quiz: Quiz) {
        quizAdapter.submitList(quiz.questions)
    }

    private fun setPosition(position: Int) {
        if (binding.fqViewPager.currentItem != position)
            binding.fqViewPager.setCurrentItem(position, false)
    }

    private fun setCheckedProgress(checkedProgress: Int) {
        submitAdapter.updateCheckedProgress(checkedProgress)
    }

    private fun setProgress(progress: Int) {
        binding.fqPbQuizProgress.progress = progress
    }
}