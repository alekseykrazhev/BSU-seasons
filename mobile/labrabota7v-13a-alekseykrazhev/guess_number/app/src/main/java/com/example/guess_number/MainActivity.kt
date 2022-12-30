package com.example.guess_number

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.btn).setOnClickListener {
            checkGuess()
            val anim = AnimationUtils.loadAnimation(applicationContext, R.anim.animation)
            val btn = findViewById<Button>(R.id.btn)
            btn.startAnimation(anim)

            val scale = Random.nextDouble(0.3, 1.0).toFloat()
            btn.animate().scaleX(scale).scaleY(scale).duration = 200
        }
    }

    @SuppressLint("SetTextI18n")
    private fun checkGuess() {
        val bar = findViewById<ProgressBar>(R.id.progressBar)
        val numberToCheck = findViewById<EditText>(R.id.et).text.toString()
        val number = numberToCheck.toDoubleOrNull()

        bar.progress += 1

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

        if (bar.progress == bar.max) {
            showDialog("10 tries exceeded, dropping progress...")
            bar.progress = 0
            return
        }

        val randomGen = (left_bound..right_bound).random().toDouble()

        val check = checkNumber(number)

        if (check != "all good") {
            showDialog(check)
            return
        }

        if (number == randomGen) {
            showDialog("Good job, you guessed!")
            return
        }

        if (number != randomGen) {
            showDialog("Too bad, try again...")
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

    private fun showDialog(message: String) {
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage(message)
        val res = dialog.create()
        res.show()
    }
}
