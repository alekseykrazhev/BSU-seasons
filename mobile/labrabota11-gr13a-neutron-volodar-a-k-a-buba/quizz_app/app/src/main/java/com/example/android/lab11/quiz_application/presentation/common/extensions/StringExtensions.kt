package com.example.android.lab11.quiz_application.presentation.common.extensions

import android.text.Html

fun String.fromHtml(): String =
    Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()