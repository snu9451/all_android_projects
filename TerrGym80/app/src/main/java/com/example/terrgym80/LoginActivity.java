package com.example.terrgym80;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements VolleyCallback {

    public static final String TAG = "LoginActivity";
    private Map<String, String> pmap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginInit(View v) {
        EditText etm_id = findViewById(R.id.etm_id);
        EditText etm_pw = findViewById(R.id.etm_pw);
        String id = etm_id.getText().toString();
        String pw = etm_pw.getText().toString();
        pmap.put("mem_id", id);
        pmap.put("mem_pw", pw);
        Log.i(TAG, "사용자가 입력한 값 ====> " + pmap);
    }

    public void loginProcess(Map<String, String> pmap) {
        Log.i(TAG, "loginProcess ====> " + pmap);
        Toast.makeText(this, MemberDTO.getInstance().getMem_name() + "님 환영합니다."
                , Toast.LENGTH_LONG).show();

    }

    @Override
    public void onResponse(String response) {
        if () {
            Toast.makeText(getApplication()
                    , "아이디가 존재하지 않습니다."
                    , Toast.LENGTH_LONG).show();

        } else if () {
            Toast.makeText(getApplication()
                    , "아이디가 존재하지 않습니다."
                    , Toast.LENGTH_LONG).show();

        } else {
            for (Map.Entry dtoTOMap : resultList.get(0).entrySet)

        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("error", error.toString());
    },"login/jsonLogin",pmap);
        Toast.makeText(this,MemberDTO.getInstance().

    getMem_name()+"님 환영합니다.",Toast.LENGTH_LONG).

    show();
        this.

    finish();


}