package com.example.calculatortest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_multi.*
import kotlinx.android.synthetic.main.activity_plus.*

class MultiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi)
        val num1 = intent.getStringExtra("num1")!!.toInt()
        val num2 = intent.getStringExtra("num2")!!.toInt()

        val result = num1 * num2
        multiResult.text = result.toString()
    }
}