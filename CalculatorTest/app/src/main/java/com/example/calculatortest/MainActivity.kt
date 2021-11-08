package com.example.calculatortest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
        plusbutton.setOnClickListener {
            saveData(editText1.text.toString().toInt(),
                editText2.text.toString().toInt())
            startActivity<PlusActivity>(
                "num1" to editText1.text.toString(),
                "num2" to editText2.text.toString()
            )
        }
        minusbutton.setOnClickListener {
            saveData(editText1.text.toString().toInt(),
                editText2.text.toString().toInt())
            startActivity<MinusActivity>(
                "num1" to editText1.text.toString(),
                "num2" to editText2.text.toString()
            )
        }
        mulbutton.setOnClickListener {
            saveData(editText1.text.toString().toInt(),
                editText2.text.toString().toInt())
            startActivity<MultiActivity>(
                "num1" to editText1.text.toString(),
                "num2" to editText2.text.toString()
            )
        }
        divbutton.setOnClickListener {
            saveData(editText1.text.toString().toInt(),
                editText2.text.toString().toInt())
            startActivity<divActivity>(
                "num1" to editText1.text.toString(),
                "num2" to editText2.text.toString()
            )
        }
    }
    private fun saveData(height : Int, weight : Int) {

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("KEY_num1", height)
            .putInt("KEY_num2", weight)
            .apply()
    }

    private fun loadData(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val num1 = pref.getInt("KEY_num1", 0)
        val num2 = pref.getInt("KEY_num2", 0)

        if(num1 != 0 && num2 != 0){
            editText1.setText(num1.toString())
            editText2.setText(num2.toString())
        }
    }
}