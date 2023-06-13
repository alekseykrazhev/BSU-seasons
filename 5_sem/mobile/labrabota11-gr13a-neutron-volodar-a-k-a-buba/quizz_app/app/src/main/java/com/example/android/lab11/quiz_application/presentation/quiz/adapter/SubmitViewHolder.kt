package com.example.android.lab11.quiz_application.presentation.quiz.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.android.lab11.quiz_application.R
import com.example.android.lab11.quiz_application.databinding.ViewHolderSubmitBinding

class SubmitViewHolder(
    private val binding: ViewHolderSubmitBinding,
    private val submitListener: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.fqBtnSubmitQuiz.setOnClickListener {
            submitListener()
        }
    }

    fun bind(checkedProgress: Int) {
        with(binding) {
            vhqTvCheckedProgress.text =
                root.context.getString(R.string.quiz_you_answered, checkedProgress)
        }
    }
}