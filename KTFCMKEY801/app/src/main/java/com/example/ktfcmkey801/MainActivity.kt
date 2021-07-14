package com.example.ktfcmkey801

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.messaging.FirebaseMessaging

// ":" > 상속
class MainActivity : AppCompatActivity() {

    private val resultTextView: TextView by lazy {
        findViewById(R.id.resultTextView)
    }

    private val firebaseToken: TextView by lazy {
        findViewById(R.id.firebaseTokenText)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("태그", "생성")
        initFirebase()
    }

    // 토큰 값 받아오기
    private fun initFirebase(){
        // getInstance > 싱글톤
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    firebaseToken.text = task.result
                }
                else {
                    Log.i("MainActivity", "출력출력")
                }
            }
    }
}