package com.example.android.lab11.quiz_application.presentation.quiz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.android.lab11.quiz_application.databinding.ViewHolderQuestionBinding
import com.example.android.lab11.quiz_application.presentation.quiz.entity.Question

class QuizViewPagerAdapter(
    private val checkListener: (position: Int, checkedAnswer: String) -> Unit
) : ListAdapter<Question, QuestionViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        QuestionViewHolder(
            ViewHolderQuestionBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            checkListener
        )

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Question>() {

        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean =
            oldItem.question == newItem.question

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean =
            oldItem == newItem
    }

}