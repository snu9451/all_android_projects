package com.example.bmi80

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val heightEditText: EditText = findViewById(R.id.heightEditText)
        val weightEditText: EditText = findViewById(R.id.weightEditText)
        val resultButton = findViewById<Button>(R.id.resultButton)
        resultButton.setOnClickListener {
            if( heightEditText.text.isEmpty() || weightEditText.text.isEmpty() ) {
                Toast.makeText(this, "빈 값이 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // isEmpty를 붙여서 유효성을 체크했으므로 절대 빈 값이 올 수 없다.
            // 코틀린에서는 nullsafe 상태인지 nullable 상태인지를 체크해야 함
            val height: Int = heightEditText.text.toString().toInt()
            val weight: Int = weightEditText.text.toString().toInt()

            // 값을 넘기기 위해 인텐트 생성
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("height", height)
            intent.putExtra("weight", weight)

            startActivity(intent)
        }
    }
}