package com.example.calculator14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.btn).setOnClickListener {
            calculate()
        }
    }

    private fun calculate() {
        val len = findViewById<EditText>(R.id.edit_len).text.toString().toDoubleOrNull()
        val deg = findViewById<EditText>(R.id.edit_a).text.toString().toDoubleOrNull()
        val hord = findViewById<EditText>(R.id.edit_hord).text.toString().toDoubleOrNull()
        val res = findViewById<TextView>(R.id.textView)

        if (len == null || deg == null || hord == null) {
            res.requestFocus()
            res.error = "Wrong params!!"
            return
        } else {
            res.error = null
        }

        val r = hord.div((sqrt(2 - (2 * kotlin.math.cos(deg)))))
        val result = 0.5 * len * r

        if (result.isNaN() || result.isInfinite()) {
            res.requestFocus()
            res.error = "Wrong params"
            return
        } else {
            res.error = null
        }

        res.text = result.toString()
        return
    }
}