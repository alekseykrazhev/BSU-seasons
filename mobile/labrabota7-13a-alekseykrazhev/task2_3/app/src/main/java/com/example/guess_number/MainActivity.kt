package com.example.guess_number

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.btn).setOnClickListener {
            checkGuess()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun checkGuess() {
        val numberToCheck = findViewById<EditText>(R.id.et).text.toString()
        val number = numberToCheck.toDoubleOrNull()

        var left_bound = 1
        var right_bound = 100
        val left = findViewById<EditText>(R.id.left).text.toString().toIntOrNull()
        val right = findViewById<EditText>(R.id.right).text.toString().toIntOrNull()

        if (left != null) {
            left_bound = left
        }

        if (right != null) {
            right_bound = right
        }

        val result = findViewById<TextView>(R.id.tv)

        if (left_bound >= right_bound) {
            result.text = "Wrong bounds."
            return
        }

        val randomGen = (left_bound..right_bound).random().toDouble()

        val check = checkNumber(number)

        if (check != "all good") {
            result.text = check
            return
        }

        if (number == randomGen) {
            result.text = "You guessed!"
            return
        }

        if (number != randomGen) {
            result.text = "Try again."
            return
        }
    }

    private fun checkNumber(number: Double?): String {
        if (number == null) {
            return "Not a number!"
        }

        if (number < 1 || number > 100) {
            return "Out of bounds."
        }

        return "all good"
    }
}
