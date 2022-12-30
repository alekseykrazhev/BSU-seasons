package com.example.task1

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.GestureDetector
import android.view.GestureDetector.OnDoubleTapListener
import android.view.MotionEvent
import android.view.View
import android.widget.TextView

class MainActivity : Activity(), GestureDetector.OnGestureListener,
    OnDoubleTapListener {
    var tvOutput: TextView? = null
    var mDetector: GestureDetector? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvOutput = findViewById<View>(R.id.tw) as TextView
        mDetector = GestureDetector(this, this)
        mDetector!!.setOnDoubleTapListener(this)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDetector!!.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    @SuppressLint("SetTextI18n")
    override fun onDown(event: MotionEvent): Boolean {
        tvOutput!!.text = "onDown: $event"
        return false
    }

    @SuppressLint("SetTextI18n")
    override fun onFling(
        event1: MotionEvent, event2: MotionEvent,
        velocityX: Float, velocityY: Float
    ): Boolean {
        tvOutput!!.text = "onFling: $event1$event2"
        return true
    }

    @SuppressLint("SetTextI18n")
    override fun onLongPress(event: MotionEvent) {
        tvOutput!!.text = "onLongPress: $event"
    }

    @SuppressLint("SetTextI18n")
    override fun onScroll(
        e1: MotionEvent, e2: MotionEvent, distanceX: Float,
        distanceY: Float
    ): Boolean {
        tvOutput!!.text = "onScroll: $e1$e2"
        return true
    }

    @SuppressLint("SetTextI18n")
    override fun onShowPress(event: MotionEvent) {
        tvOutput!!.text = "onShowPress: $event"
    }

    @SuppressLint("SetTextI18n")
    override fun onSingleTapUp(event: MotionEvent): Boolean {
        tvOutput!!.text = "onSingleTapUp: $event"
        return true
    }

    @SuppressLint("SetTextI18n")
    override fun onDoubleTap(event: MotionEvent): Boolean {
        tvOutput!!.text = "onDoubleTap: $event"
        return true
    }

    @SuppressLint("SetTextI18n")
    override fun onDoubleTapEvent(event: MotionEvent): Boolean {
        tvOutput!!.text = "onDoubleTapEvent: $event"
        return true
    }

    @SuppressLint("SetTextI18n")
    override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
        tvOutput!!.text = "onSingleTapConfirmed: $event"
        return true
    }
}