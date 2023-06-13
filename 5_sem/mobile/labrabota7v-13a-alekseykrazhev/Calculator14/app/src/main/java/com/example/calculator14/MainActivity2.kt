package com.example.calculator14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.cos
import kotlin.math.sqrt
import kotlin.time.Duration.Companion.seconds

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val button = findViewById<Button>(R.id.back_button).setOnClickListener {
            goBack()
        }
        setResult()
    }

    private fun goBack() {
        finish()
        super.onBackPressed()
        overridePendingTransition(
            R.anim.anim_enter, R.anim.anim_exit)
    }

    private fun setResult() {
        val res = findViewById<TextView>(R.id.res)

        val intent = intent
        res.text = intent.getStringExtra("result")
    }
}