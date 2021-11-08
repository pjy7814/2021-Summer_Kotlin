package com.example.lottonumgenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_constellation.*
import kotlinx.android.synthetic.main.activity_name.*
import kotlinx.android.synthetic.main.activity_result.*
import java.text.SimpleDateFormat
import java.util.*

class NameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)
        goButton.setOnClickListener {
            if(TextUtils.isEmpty(editText.text.toString())) {
                Toast.makeText(applicationContext, "이름을 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this, ResultActivity::class.java)
            intent.putIntegerArrayListExtra("result",
                ArrayList(LottoNumberMaker.getLottoNumbersFromHash(editText.text.toString())))
            intent.putExtra("name", editText.text.toString())
            startActivity(intent)
        }
        backButton.setOnClickListener {
            finish()
        }
    }
    fun getLottoNumbersFromHash(str: String): MutableList<Int> {
// 1 ~ 45 번에 로또 번호를 저장할 리스트 생성
        val list = mutableListOf<Int>()
// 1~45 까지 for 문을 돌면서 리스트에 로또 번호 저장
        for (number in 1..45) {
            list.add(number)
        }
// SimpleDateFormat 은 날짜의 시간값을 포맷화된 텍스트 형태로 바꿔주는 클래스
// 현재 Date 의 "yyyy-MM-dd" 문자열과 이름 문자열을 합친다 ➔ 2019-04-26-홍길동.hashCode()
        val targetString = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(Date()) + str
//시간 마다 다른 번호로 하려면 HH 포멧 추가
// 리스트를 무작위로 섞는다. 이때 섞는 기준으로 Random(SEED) 를 사용한다
// SEED 값은 전달받은 이름과 오늘의 해당하는 "yyyy-MM-dd" 를 합친 문자열의 해시코드를 사용한다.
        list.shuffle(Random(targetString.hashCode().toLong()))
// 리스트를 앞에서부터 순서대로 6개를 짤라 결과 반환
        return list.subList(0, 6)
    }
}