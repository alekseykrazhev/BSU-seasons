package com.example.calculator14

import android.content.Intent
import android.gesture.GestureLibraries
import android.gesture.GestureLibrary
import android.gesture.GestureOverlayView
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt


class MainActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.btn).setOnClickListener {
            calculate()
        }

        val editText = findViewById<EditText>(R.id.edit_len)
        val gestureOverlayView = findViewById<GestureOverlayView>(R.id.gesture)
        gestureInput(editText, gestureOverlayView)

        val editText1 = findViewById<EditText>(R.id.edit_a)
        val gestureOverlayView1 = findViewById<GestureOverlayView>(R.id.gesture1)
        gestureInput(editText1, gestureOverlayView1)

        val editText2 = findViewById<EditText>(R.id.edit_hord)
        val gestureOverlayView2 = findViewById<GestureOverlayView>(R.id.gesture2)
        gestureInput(editText2, gestureOverlayView2)
    }

    private fun gestureInput(editText: EditText, gestureOverlayView: GestureOverlayView) {
        val gestureLibrary: GestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures)
        if (!gestureLibrary.load()) {
            finish()
        }

        val finish = findViewById<Button>(R.id.btn)

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
                        "=" -> finish.performClick()
                        else -> Toast.makeText(this, "Unknown gesture", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun calculate() {
        val len = findViewById<EditText>(R.id.edit_len).text.toString().toDoubleOrNull()
        val deg = findViewById<EditText>(R.id.edit_a).text.toString().toDoubleOrNull()
        val hord = findViewById<EditText>(R.id.edit_hord).text.toString().toDoubleOrNull()

        var result_ = ""

        if (len == null || deg == null || hord == null) {
            val trans = fragmentManager.beginTransaction()
            trans.add(R.id.frameLayout, com.example.calculator14.Fragment())
            trans.addToBackStack(null)
            trans.commit()
            return
        } else {
            val trans = fragmentManager.beginTransaction()
            for (fragment in fragmentManager.fragments){
                trans.remove(fragment)
            }
            trans.commit()
        }

        val r = hord.div((sqrt(2 - (2 * kotlin.math.cos(deg)))))
        val result = 0.5 * len * r

        if (result.isNaN() || result.isInfinite()) {
            val trans = fragmentManager.beginTransaction()
            trans.add(R.id.frameLayout, com.example.calculator14.Fragment())
            trans.addToBackStack(null)
            trans.commit()
            return
        } else {
            val trans = fragmentManager.beginTransaction()
            for (fragment in fragmentManager.fragments){
                trans.remove(fragment)
            }
            trans.commit()
        }

        result_ = result.toString()

        val goToNextActivity = Intent(applicationContext, MainActivity2::class.java)
        goToNextActivity.putExtra("result", result_)
        startActivity(goToNextActivity)
        overridePendingTransition(
            R.anim.anim_enter, R.anim.anim_exit)
    }
}
