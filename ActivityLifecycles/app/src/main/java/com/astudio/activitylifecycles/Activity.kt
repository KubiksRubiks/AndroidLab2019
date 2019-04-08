package com.astudio.activitylifecycles

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast

class Activity: AppCompatActivity(){

    private val TAG = "Жизненный цикл"
    private var mInfoTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mInfoTextView = findViewById(R.id.textView) as TextView
        Toast.makeText(getApplicationContext(), "onCreate()", Toast.LENGTH_SHORT).show()
        Log.i(TAG, "onCreate()")
    }

    override  fun onStart() {
        super.onStart()

        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show()
        Log.i(TAG, "onStart()")
    }

    override  fun onResume() {
        super.onResume()

        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show()
        Log.i(TAG, "onResume()")
    }

    override  fun onPause() {
        super.onPause()

        Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show()
        Log.i(TAG, "onPause()")
    }

    override  fun onStop() {
        super.onStop()

        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show()
        Log.i(TAG, "onStop()")
    }

    override  fun onRestart() {
        super.onRestart()

        Toast.makeText(getApplicationContext(), "onRestart()", Toast.LENGTH_SHORT).show()
        Log.i(TAG, "onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()

        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show()
        Log.i(TAG, "onDestroy()")
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.buttonClick -> mInfoTextView!!.text = "Приложение уже было запущено!"
            R.id.buttonExit -> finish()

            else -> {
            }
        }
    }

}