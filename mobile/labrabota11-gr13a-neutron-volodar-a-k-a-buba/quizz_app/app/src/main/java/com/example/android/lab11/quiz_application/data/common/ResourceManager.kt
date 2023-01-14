package com.example.android.lab11.quiz_application.data.common

import android.content.Context
import androidx.annotation.StringRes

class ResourceManager(private val context: Context) {

    fun getString(@StringRes resId: Int): String = context.getString(resId)

}