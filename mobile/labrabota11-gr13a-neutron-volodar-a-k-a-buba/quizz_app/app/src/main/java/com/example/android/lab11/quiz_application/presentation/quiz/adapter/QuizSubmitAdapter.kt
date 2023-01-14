package com.example.android.lab11.quiz_application.presentation.quiz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.lab11.quiz_application.databinding.ViewHolderSubmitBinding

class QuizSubmitAdapter(
    private val submitListener: () -> Unit
) : RecyclerView.Adapter<SubmitViewHolder>() {

    private var checkedProgress: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SubmitViewHolder(
        ViewHolderSubmitBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
        submitListener
    )

    override fun onBindViewHolder(holder: SubmitViewHolder, position: Int) {
        holder.bind(checkedProgress)
    }

    override fun getItemCount(): Int = 1

    fun updateCheckedProgress(checkedProgress: Int) {
        this.checkedProgress = checkedProgress
        notifyItemChanged(0)
    }
}