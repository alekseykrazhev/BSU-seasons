package com.example.noteapp


import android.annotation.SuppressLint
import android.content.Context
import android.gesture.GestureLibraries
import android.gesture.GestureLibrary
import android.gesture.GestureOverlayView
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText)

        val clear = findViewById<Button>(R.id.clear_text_btn).setOnClickListener {
            val clearEditField = findViewById<EditText>(R.id.editText)
            clearEditField.setText("")
        }

        gestureInput()
    }

    private fun gestureInput() {
        val gestureLibrary: GestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures)
        if (!gestureLibrary.load()) {
            finish()
        }

        val gestureOverlayView = findViewById<GestureOverlayView>(R.id.gesture_field)
        gestureOverlayView.addOnGesturePerformedListener { _, gesture ->
            val numbers = gestureLibrary.recognize(gesture)
            if (numbers.size > 0) {
                val number = numbers[0]
                if (number.score > 1.0) {
                    when (number.name) {
                        "a" -> editText.append("a")
                        "b" -> editText.append("b")
                        "c" -> editText.append("c")
                        "d" -> editText.append("d")
                        "e" -> editText.append("e")
                        "g" -> editText.append("g")
                        "h" -> editText.append("h")
                        "i" -> editText.append("i")
                        "f" -> editText.append("f")
                        "j" -> editText.append("j")
                        "k" -> editText.append("k")
                        "l" -> editText.append("l")
                        "m" -> editText.append("m")
                        "n" -> editText.append("n")
                        "o" -> editText.append("o")
                        "p" -> editText.append("p")
                        "q" -> editText.append("q")
                        "r" -> editText.append("r")
                        "s" -> editText.append("s")
                        "t" -> editText.append("t")
                        "u" -> editText.append("u")
                        "w" -> editText.append("w")
                        "x" -> editText.append("x")
                        "y" -> editText.append("y")
                        "z" -> editText.append("z")
                        else -> Toast.makeText(applicationContext, "Unknown gesture.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val userInputToOpenName = findViewById<EditText>(R.id.filename_open_field).text.toString()
        val fileName = "${userInputToOpenName}.txt"

        val userInputToSaveName = findViewById<EditText>(R.id.filename_save_field).text.toString()
        val s = "${userInputToSaveName}.txt"

        return when (item.itemId) {
            R.id.action_open -> {
                openFile(fileName)
                val clearOpenField = findViewById<EditText>(R.id.filename_open_field)
                clearOpenField.setText("")
                true
            }
            R.id.action_save -> {
                saveFile(s)
                val clearSaveField = findViewById<EditText>(R.id.filename_save_field)
                clearSaveField.setText("")
                true
            }
            else -> true
        }
    }

    private fun openFile(fileName: String) {
        val textFromFile =
            File(applicationContext.filesDir, fileName)
                .bufferedReader()
                .use { it.readText(); }
        editText.setText(textFromFile)
    }

    private fun saveFile(fileName: String) {
        applicationContext.openFileOutput(fileName, Context.MODE_PRIVATE).use {
            it.write(editText.text.toString().toByteArray())
        }
        Toast.makeText(applicationContext, "File saved.", Toast.LENGTH_SHORT).show()
    }
}