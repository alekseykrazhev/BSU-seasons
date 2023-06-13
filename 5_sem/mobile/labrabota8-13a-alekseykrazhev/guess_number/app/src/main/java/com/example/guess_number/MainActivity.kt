package com.example.guess_number

import android.annotation.SuppressLint
import android.gesture.GestureLibraries
import android.gesture.GestureLibrary
import android.gesture.GestureOverlayView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.*
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

            val editTextLeft = findViewById<EditText>(R.id.left)
            val gestureOverlayView = findViewById<GestureOverlayView>(R.id.gesture)
            gestureInput(editTextLeft, gestureOverlayView)

            val editTextRight = findViewById<EditText>(R.id.right)
            val gestureOverlayView1 = findViewById<GestureOverlayView>(R.id.gesture1)
            gestureInput(editTextRight, gestureOverlayView1)

            val editText = findViewById<EditText>(R.id.et)
            val gestureOverlayView2 = findViewById<GestureOverlayView>(R.id.gesture2)
            gestureInput(editText, gestureOverlayView2)
        }
    }

    private fun gestureInput(editText: EditText, gestureOverlayView: GestureOverlayView) {
        val gestureLibrary: GestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures)
        if (!gestureLibrary.load()) {
            finish()
        }

        gestureOverlayView.addOnGesturePerformedListener { _, gesture ->
            val numbers = gestureLibrary.recognize(gesture)
            if (numbers.size > 0) {
                val number = numbers[0]
                if (number.score > 1.0) {
                    when (number.name) {
                        "0" -> editText.append("0")
                        "1" -> editText.append("1")
                        "2" -> editText.append("2")
                        "3" -> editText.append("3")
                        "4" -> editText.append("4")
                        "5" -> editText.append("5")
                        "6" -> editText.append("6")
                        "7" -> editText.append("7")
                        "8" -> editText.append("8")
                        "9" -> editText.append("9")
                        else -> Toast.makeText(this, "Unknown gesture", Toast.LENGTH_SHORT).show()
                    }
                }
            }
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
