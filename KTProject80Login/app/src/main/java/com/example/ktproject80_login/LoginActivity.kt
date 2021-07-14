package com.example.ktproject80_login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity: AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    //페이스북 로그인 추가
    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth
        //페이스북 로그인 추가
        callbackManager = CallbackManager.Factory.create()

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val signUpButton = findViewById<Button>(R.id.signUpButton)
        //페이스북 로그인 버튼 추가

        val facebookLoginButton = findViewById<LoginButton>(R.id.facebookLoginButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
//                .addOnCompleteListener(new OnCompleteListener(이벤트 객체){
//                    override 함수명(){
//                        구현부
//                    }
//                })
            auth.signInWithEmailAndPassword(email, password)
                    // 람다식
                    // cf - 자바스크립트에서의 arrow function
                    // { (event) -> {} }
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        successLogin()
                    } else {
                        Toast.makeText(
                            this,
                            "로그인에 실패했습니다. 이메일 또는 비밀번호를 확인해주세요.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }////////////////////////////////end of 로그인

        emailEditText.addTextChangedListener {
            // (Java) EditText et_email = findViewBtId(R.id.xxx).getText().toString())
            // 관심 가져서 볼 부분: getText가 아니라 text가 쓰이고 있다.
            // 타입은 boolean
            // cf) (Java) btn.setEnabled(true)였는데 이처럼 파라미터로 처리하지 않고 대입연산자를 이용해 처리한다.
            val enable = emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()
            loginButton.isEnabled = enable
            signUpButton.isEnabled = enable
        }

        passwordEditText.addTextChangedListener {
            val enable = emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()
            loginButton.isEnabled = enable
            signUpButton.isEnabled = enable
        }

        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this,
                            "회원가입을 성공했습니다. 로그인 버튼을 눌러 로그인해주세요.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(this, "이미 가입한 이메일이거나, 회원가입에 실패했습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

        }
        //페이스북 로그인 버튼 추가

        callbackManager = CallbackManager.Factory.create()
        facebookLoginButton.setPermissions("email", "public_profile")
        facebookLoginButton.registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d("Facebook", "facebook:onSuccess:$loginResult")
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                Log.d("Facebook", "facebook:onCancel")
            }

            override fun onError(error: FacebookException) {
                Log.d("Facebook", "facebook:onError", error)
                Toast.makeText(this@LoginActivity, "페이스북 로그인에 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            }
        })

    }


    //페이스북 로그인 추가 코드
    // 페이스북 요청에 대한 처리를 진행하고 그 과정에서 필요한 토큰을 파라미터로 받아서 진행함
    private fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    successLogin()
                } else {
                    Toast.makeText(this, "페이스북 로그인에 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        //페이스북에서 로그인 처리되고 그 결과를 받아오는 부분
//        callbackManager.onActivityResult(requestCode, resultCode, data)
    }


    private fun successLogin() {
        if (auth.currentUser == null) {
            Toast.makeText(this, "로그인에 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        val userId: String = auth.currentUser!!.uid
        //파이어베이스 그레이들에 firebase-database-ktx등록
        //json형식으로 저장된다. child.child로 가져올 수 있다.
        val currentUserDb = Firebase.database.reference.child("Users").child(userId)
        // Map<String, Object> pmap = new HashMap<>();
        // Map<String, T> pmap = new HashMap<>();
        val user = mutableMapOf<String, Any>()
        // user.put("userId", "test");
        user["userId"] = userId
        // 파이어베이스 리얼데이터베이스 업데이트 - JSON 포매, No SQL
        currentUserDb.updateChildren(user)
        //LoginActivity를 종료한다.
        finish()
    }
}