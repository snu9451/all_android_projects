package com.example.ktproject80_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

/*
    onCreate() - onStart() - background에 있다가 다시 foreground로 올라올 때: onResume()

    userId가 null이면 다시 로그인부터 시작하라고 할 수 있겠다.
*/
class MainActivity : AppCompatActivity() {

    // 자바 스타일의 싱글톤 패턴 구현 방법
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    // 한번 호출되면 끝임 따라서 onStart를 같이 사용한다.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val emailEditText = findViewById<EditText>(R.id.emailEditText)
//        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)

        findViewById<TextView>(R.id.helloWorldTextView).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun onStart() {
        super.onStart()
        if(auth.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            // 다른 액티비티로 이동하는 경우 현재의 액티비티는 끝내기
            // finish()
        }
    }
}