package com.example.tomcatconnect2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv_receive = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 화면 구성 시 바로 초기화
        tv_receive = findViewById(R.id.tv_receive);
    }

    public void loginAction(View view){
        Toast.makeText(getApplicationContext(), "로그인 버튼클릭!", Toast.LENGTH_LONG).show();
        println("요청 보냄");
        // GET 방식으로 넘길, 입력된 아이디와 패스워드 담기
        EditText et_id = findViewById(R.id.et_id);
        String id = et_id.getText().toString();
        EditText et_pw = findViewById(R.id.et_pw);
        String pw = et_pw.getText().toString();
        // 톰캣 서버에서 전송한 문자열을 받는 변수(Text, JSON)
        String receive = null;
        /*
            네트워크, 통신, Thread, DB연동, (myBatis와 같이)외부와의 연결,
            클라우드...와 관련되었다면 반!드!시! 예외처리 해주는 것이 예의이고 정답이다.
        */
        try {
            LoginLogic loginLogic = new LoginLogic();
            receive = loginLogic.execute(id, pw).get();
            tv_receive.setText(receive);
        } catch (Exception e){
            Log.i(this.getClass().getName(), "Exception: "+e.toString());
        }
        Log.i(this.getClass().getName(), "톰캣 서버에서 전송한 문자열: "+receive);
    }
    public void println(String data){
        // info 레벨로 로그를 찍을 수 있음.
        // 로그캣에 대한 설정도 info 레벨로 맞춰주면 됨. (양이 너무 많아~)
        Log.i("MainActivity", data+"\n");
    }
}