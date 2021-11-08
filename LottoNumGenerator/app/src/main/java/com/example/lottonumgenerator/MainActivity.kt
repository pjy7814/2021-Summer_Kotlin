package com.example.lottonumgenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lottonumgenerator.LottoNumberMaker.getRandomLottoNumber
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        randomCard.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putIntegerArrayListExtra("result", ArrayList(LottoNumberMaker.getShuffleLottoNumbers()))
            startActivity(intent)
        }
        constellationCard.setOnClickListener {
            startActivity(Intent(this, ConstellationActivity::class.java))
        }
        nameCard.setOnClickListener {
            startActivity(Intent(this, NameActivity::class.java))
        }

    }

    fun getRandomLottoNumbers(): MutableList<Int> {
// 무작위로 생성된 로또 번호를 저장할 가변 리스트 생성
        val lottoNumbers = mutableListOf<Int>() //변경 가능한 리스트 타입
// 6번 반복하는 for 문
        for (i in 1..6) {
// 랜덤한 번호를 임시로 저장할 변수를 생성
            var number = 0
            do {
// 랜덤한 번호를 추출해 number 변수에 저장
                number = getRandomLottoNumber()
// lottoNumbers 에 number 변수의 값이 없을때까지 반복
            } while (lottoNumbers.contains(number)) //동일한 수가 있다면 true
// 이미 뽑은 리스트에 없는 번호가 나올때까지 반복했으므로 중복이 없는 상태
// 추출된 번호를 뽑은 리스트에 추가
            lottoNumbers.add(number)
        }
        return lottoNumbers
    }



}