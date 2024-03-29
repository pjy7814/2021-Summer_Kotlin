package com.example.flashlight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val torch = Torch(this)
        flashSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
//On일 때 동작
                startService(intentFor<TorchService>().setAction("on"))
            } else {
//Off일 때 동작
                startService(intentFor<TorchService>().setAction("off"))
            }
        }
    }
}