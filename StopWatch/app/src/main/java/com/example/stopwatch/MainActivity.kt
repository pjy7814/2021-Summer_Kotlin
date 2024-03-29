package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startFab.setOnClickListener {
            isRunning = !isRunning

            if(isRunning){
                start()
            } else {
                pause()
            }
        }
        lapButton.setOnClickListener {
            recordLapTime()
        }
        resetFab.setOnClickListener {
            reset()
        }
    }
    private var time = 0
    private var timerTask : Timer? = null
    private var isRunning = false
    private var lap = 1
    private fun start(){
        startFab.setImageResource(R.drawable.ic_baseline_pause_24)

        timerTask = timer(period = 10){
            time++
            val sec = time / 100
            val milli = time %100
            runOnUiThread {
                secTextView.text = "$sec"
                milliTextView.text = "$milli"
            }
        }
    }
    private fun pause(){
        startFab.setImageResource(R.drawable.ic_baseline_play_arrow_24)

        timerTask?.cancel()
    }
    private fun recordLapTime(){
        val lapTime = this.time
        val textView = TextView(this)
        textView.text = "$lap LaB : ${lapTime / 100}.${lapTime %100}"

        lapLayout.addView(textView, 0)
        lap++
    }
    private fun reset(){
        timerTask?.cancel()

        time = 0
        isRunning = false
        startFab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        secTextView.text = "0"
        milliTextView.text = "00"

        lapLayout.removeAllViews()
        lap = 1
    }
}