package com.example.android.lab11.quiz_application.presentation.common.mappers

import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.example.android.lab11.quiz_application.data.common.mappers.Mapper

object AppThemeMapper : Mapper<String, Int> {

    override fun map(input: String): Int = when {
        input == Themes.DARK.name -> AppCompatDelegate.MODE_NIGHT_YES
        input == Themes.LIGHT.name -> AppCompatDelegate.MODE_NIGHT_NO
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        else -> AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
    }
}

enum class Themes {
    DARK,
    LIGHT
}