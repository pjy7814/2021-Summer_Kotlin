package com.example.todolist_realm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_edit.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import java.util.*

class EditActivity : AppCompatActivity() {

    val realm = Realm.getDefaultInstance()



    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val id = intent.getLongExtra("id",-1L)//1:Int, 1L:Long, 1.0:Double, 1.0f:Float
        if (id == -1L) { //초기화 값(기본값)인 -1이 그대로 넘어오면 추가모드
            insertMode()
        } else { //중간에 변경되었으면 수정 모드
            updateMode(id)
        }
// 캘린더뷰의 날짜를 선택했을 때 Calendar 객체에 설정
// 선택할 때마다 날짜정보를 넘겨 받음
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }


    }
    //import java.util.*
//import io.realm.kotlin.createObject
    val calendar: Calendar = Calendar.getInstance()//날짜를 다루는 캘린더 객체
    private fun insertTodo() {
        realm.beginTransaction() //트렌젝션 시작
        val todo = realm.createObject<Todo>(nextId()) //새 객체 생성
        todo.title = todoEditText.text.toString() //값 설정
        todo.count = editText2.text.toString()
        todo.address = editText3.text.toString()
        todo.date = calendar.timeInMillis
        realm.commitTransaction() //트랜젝션 종료
        alert("내용이 추가되었습니다."){ yesButton { finish() } }.show()//다이얼로그 표시
    }
    private fun nextId(): Int { //다음 id를 반환
        val maxId = realm.where<Todo>().max("id")
        if (maxId != null) {
            return maxId.toInt() + 1
        }
        return 0
    }
    private fun insertMode() {
        deletefab.visibility = View.GONE // 삭제 버튼을 감추기
//오류 발생시 deleteFab.hide()
        doneFab.setOnClickListener { // 완료 버튼을 클릭하면 추가
            insertTodo()
        }
    }
    private fun deleteTodo(id: Long) {
        realm.beginTransaction()
        val todo = realm.where<Todo>().equalTo("id", id).findFirst()!!
        todo.deleteFromRealm() // deleteFromRealm 메서드로 삭제
        realm.commitTransaction()
        Toast.makeText(applicationContext, "삭제됨", Toast.LENGTH_SHORT).show()
        /*alert("내용이 삭제되었습니다.") {
            yesButton { finish() }
        }.show()*/
    }

    private fun updateTodo(id: Long) {
        realm.beginTransaction() //트렌젝션 시작
        val todo = realm.where<Todo>().equalTo("id", id).findFirst()!!//!!:todo는 이후부터 null이 아님
        todo.title = todoEditText.text.toString() //값 설정
        todo.count = editText2.text.toString()
        todo.address = editText3.text.toString()
        todo.date = calendar.timeInMillis
        realm.commitTransaction() //트렌젝션 종료 반영
        /*alert("내용이 변경되었습니다.") { //다이얼로그 표시
            yesButton { finish() }
        }.show()*/
        Toast.makeText(applicationContext, "저장됨", Toast.LENGTH_SHORT).show()
    }

    private fun updateMode(id: Long) {
// id에 해당하는 객체를 화면에 표시
        val todo = realm.where<Todo>().equalTo("id", id).findFirst()!!

        todoEditText.setText(todo.title)
        editText2.setText(todo.count)
        editText3.setText(todo.address)
        calendarView.date = todo.date
// 완료 버튼을 클릭하면 수정
        doneFab.setOnClickListener {
            updateTodo(id)
        }
// 삭제 버튼을 클릭하면 삭제
        deletefab.setOnClickListener {
            deleteTodo(id)

        }
    }



}