package com.example.calculator2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal var numButtons = ArrayList<Button>(10)
    internal var numBtnIDs = arrayOf(R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9)
    internal var I : Int = 0
    lateinit internal var number1 : String
    lateinit internal var number2 : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData()
        plusbutton.setOnClickListener {

            val num1 = editText1.text.toString()
            val num2 = editText2.text.toString()

            if(num1==""|| num2 == "") {
                Toast.makeText(this, "숫자를 입력하세요", Toast.LENGTH_SHORT).show()
            } else{
                var result = num1.toInt() + num2.toInt()
                resultTextView.text = "계산 결과: " + result.toString()
                saveData(editText1.text.toString().toInt(),
                        editText2.text.toString().toInt())
            }

        }
        minusbutton.setOnClickListener {
            val num1 = editText1.text.toString()
            val num2 = editText2.text.toString()

            if(num1==""|| num2 == "") {
                Toast.makeText(applicationContext, "숫자를 입력하세요", Toast.LENGTH_SHORT).show()
            } else {
                var result = num1.toInt() - num2.toInt()
                resultTextView.text = "계산 결과: " + result.toString()
                saveData(editText1.text.toString().toInt(),
                        editText2.text.toString().toInt())
            }
        }
        mulbutton.setOnClickListener {
            saveData(editText1.text.toString().toInt(),
                    editText2.text.toString().toInt())
            val num1 = editText1.text.toString()
            val num2 = editText2.text.toString()

            if(num1==""|| num2 == "") {
                Toast.makeText(applicationContext, "숫자를 입력하세요", Toast.LENGTH_SHORT).show()
            } else {
                var result = num1.toInt() * num2.toInt()
                resultTextView.text = "계산 결과: " + result.toString()
            }
        }
        divbutton.setOnClickListener {

            val num1 = editText1.text.toString()
            val num2 = editText2.text.toString()

            if(num1==""|| num2 == "") {
                Toast.makeText(applicationContext, "숫자를 입력하세요", Toast.LENGTH_SHORT).show()
            }
            else if(num2 =="0"){
                Toast.makeText(applicationContext, "0으로 나눌 수 없습니다", Toast.LENGTH_SHORT).show()
            }
            else {
                var result = num1.toDouble() / num2.toDouble()
                resultTextView.text = "계산 결과: " + result.toString()
                saveData(editText1.text.toString().toInt(),
                        editText2.text.toString().toInt())
            }
        }
        delnum1.setOnClickListener {
            editText1.text = null
        }
        delnum2.setOnClickListener {
            editText2.text = null
        }
        delAll.setOnClickListener {
            editText1.text = null
            editText2.text = null
            resultTextView.text = "계산 결과:"
        }
        for(i in 0..9 step 1){
            numButtons.add(findViewById<Button>(numBtnIDs[i]))
        }
        for (i in 0..numBtnIDs.size-1 step 1){
            numButtons[i].setOnClickListener {
                if(editText1.isFocused == true){
                    number1= editText1.text.toString() + numButtons[i].getText().toString()
                    editText1.setText(number1)
                } else if (editText2.isFocused == true){
                    number2= editText2.text.toString() + numButtons[i].getText().toString()
                    editText2.setText(number2)
                } else{}
            }
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