package com.example.watchbefore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

// public class AfterActivity extends AppCompatActivity implements View.OnClickListener {
public class AfterActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after);
        if(savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        Button btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                running = true;
            }
        });
        Button btn_stop = findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                running = false;
            }
        });
        Button btn_reset = findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                seconds = 0;
                running = false;
            }
        });
        runTimer();
    }

    // 이전 상태를 저장하는 코드가 필요하다...
    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putInt("seconds", seconds);
        saveInstanceState.putBoolean("running", running);
    }

    /*
    public void start(View v) {
        running = true;
    }

    public void stop(View v) {
        running = false;
    }

    public void reset(View v) {
        running = false;
        seconds = 0;
    }
    */

    /*
    여기서는 레이아웃의 텍스트뷰 레퍼런스를 얻고 seconds변수의 값을 시, 분, 초
    형식으로 바꾼다음 텍스트뷰에 결과 표시
    만일 running값이 true이면 seconds변수의 값을 1씩 증가시킬 것.
     */
    private void runTimer() {
        final TextView tv_time = findViewById(R.id.tv_time);
        //Handler사용하면 특정코드를 미래의 특정시점에 사용할 수 있음.
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault()
                        , "%d:%02d:%02d", hours, minutes, secs);
                tv_time.setText(time);
                // running 상태가 true이면 계속해서 초(sec)를 증가 시킨다.
                if (running) {
                    seconds++;
                }
                // 1초 지연 후 다시 코드를 실행하게 된다.
                handler.postDelayed(this, 1000);
            }
        });
    }//runTimer
}