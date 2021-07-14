package com.example.watchbefore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    // 스톱워치 상에 표시할 초
    private int seconds = 0;
    // 스톱워치가 실행 중인가? true. 아닌가? false.]
    // 버튼에 대한 이벤트 발생으로 인해 상태값이 바뀌고 다른 메소드에서 이를 처리한다. (=> 전역변수)
    private boolean running;

    /* 지금까지 흐른 시간과, 현재 스톱워치가 실행 중인지에 대한 상태를 저장 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 별도의 메소드를 이용해 스톱워치를 갱신한다.
        // 액티비티가 생성되면 메소드를 시작합니다.
        runTimer(); // 함수 호출
    }

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
