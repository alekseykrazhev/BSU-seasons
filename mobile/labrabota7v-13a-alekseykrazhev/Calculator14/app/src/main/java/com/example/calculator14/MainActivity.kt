package com.example.calculator14

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlin.math.sqrt


class MainActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager

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
