package com.example.bmi80

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

/*****************************[[ K O T L I N 문법 ]]*************************
 * new 예약어가 없다.
 * 함수 뒤에 제네릭을 사용한다.
 * 객체 생성 후 즉시 초기화를 할 때 applay(){} let() ... 을 사용한다. - RecyclerView, XXXAdapter, ViewHolder ...
 * 출력할 때, react에서 처럼 "$변수명"의 형태로 변수를 호출한다.
 * getIntent().getIntExtra, getIntent().getStringExtra 로 쓰던 것이 intent.getIntExtra, intent.getStringExtra로 바뀌었다.
 * (get을 떼고 있는 추세)
 * switch문을 대체하는 when
 * for문의 경우 프로시저나 visual basic 문법과 동일하다.
 **************************************************************************/
class ResultActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // xml, 리사이클러뷰 root, 레이아웃매니저
        setContentView(R.layout.activity_result)
        
        // intent로 받아온 정보를 뿌리기
        // intent를 따로 인스턴스화 하지 않고도 사용하고 있음
        val height = intent.getIntExtra("height", 0)
        val weight = intent.getIntExtra("weight", 0)
        Log.d("ResultActivity", "height: $height, weight: $weight")

        // BMI 계산하기
        // pow : mathematics에 있는 함수임 (import 필요)
        val bmi = weight / (height / 100.0).pow(2.0)
        val resultText = when { // 자바의 switch-case문에 대응
            bmi >= 35.0 -> "고도 비만"
            bmi >= 30.0 -> "중정도 비만"
            bmi >= 25.0 -> "경도 비만"
            bmi >= 23.0 -> "과체중"
            bmi >= 18.5 -> "정상체중"
            else -> "저체중"
        }

        // 계산된 결과를 화면에 출력해주기
        val bmiResultTextView = findViewById<TextView>(R.id.bmiResultTextView)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        // bmiResultTextView.setText("...") -> setter가 빠지는 추세라 권장되지 않음
        bmiResultTextView.text = bmi.toString().substring(0,5)
        resultTextView.text = resultText
    }
}