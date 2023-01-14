package com.example.android.lab11.quiz_application.presentation.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.android.lab11.quiz_application.R
import com.example.android.lab11.quiz_application.presentation.common.mappers.AppThemeMapper

class SettingsFragment : PreferenceFragmentCompat() {

    private val preferenceChangeListener = Preference.OnPreferenceChangeListener { _, newValue ->
        if (newValue is String) {
            val nightMode = AppThemeMapper.map(newValue)
            updateTheme(nightMode)
        }
        true
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.fragment_settings, rootKey)

        setChangeListener()
    }

    private fun setChangeListener() {
        val preferences: Preference? = findPreference(resources.getString(R.string.key_theme))
        preferences?.onPreferenceChangeListener = preferenceChangeListener
    }

    private fun updateTheme(nightMode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        return true
    }
}