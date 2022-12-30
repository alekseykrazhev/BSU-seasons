package com.example.task2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.asLiveData
import androidx.lifecycle.observe
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var userManager: UserManager
    var age = 0
    var fname = ""
    var lname = ""
    var gender = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get reference to our userManager class
        userManager = UserManager(dataStore)

        buttonSave()

        observeData()
    }

    private fun observeData() {

        //Updates age
        userManager.userAgeFlow.asLiveData().observe(this) {
            if (it != null) {
                age = it
                val tv_age = findViewById<TextView>(R.id.tv_age)
                tv_age.text = it.toString()
            }
        }

        //Updates firstname
        userManager.userFirstNameFlow.asLiveData().observe(this) {
            if (it != null) {
                fname = it
                val tv_fname = findViewById<TextView>(R.id.tv_fname)
                tv_fname.text = it
            }
        }

        //Updates lastname
        userManager.userLastNameFlow.asLiveData().observe(this) {
            if (it != null) {
                lname = it
                val tv_lname = findViewById<TextView>(R.id.tv_lname)
                tv_lname.text = it
            }
        }

        //Updates gender
        userManager.userGenderFlow.asLiveData().observe(this) {
            if (it != null) {
                gender = if (it) "Male" else "Female"
                val tv_gender = findViewById<TextView>(R.id.tv_gender)
                tv_gender.text = gender
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private fun buttonSave() {
        val btn_save = findViewById<Button>(R.id.btn_save)
        val et_fname = findViewById<EditText>(R.id.et_fname)
        val et_lname = findViewById<EditText>(R.id.et_lname)
        val et_age = findViewById<EditText>(R.id.et_age)
        val switch_gender = findViewById<SwitchCompat>(R.id.switch_gender)
        //Gets the user input and saves it
        btn_save.setOnClickListener {
            fname = et_fname.text.toString()
            lname = et_lname.text.toString()
            age = et_age.text.toString().toInt()
            val isMale = switch_gender.isChecked

            //Stores the values
            GlobalScope.launch {
                userManager.storeUser(age, fname, lname, isMale)
            }
        }
    }
}
