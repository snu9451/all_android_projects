package com.example.tomcatconnect2021;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/*
    새로 만든 스레드에서 UI객체에 직접 접근하는 것은 불가능하다.
    핸들러 클래스를 사용하기도 하지만, 핸들러를 사용하면 코드가 복잡해진다.
    이러한 백그라운드 작업을 심플하게 만들기 위해 AsyncTask클래스를 사용한다.
    Foreground 상태 - Background 상태(내부적으로 처리. 보이지 않음.)
    AsyncTask<String, Void, String> Background 처리를 위해 Android 쪽에서 제공하는 클래스
        ㄴ> 패키지 자체도 os(operating system)이다.
        ㄴ> [스레드를 위한 동작 코드]와 [UI 접근 코드]를 한꺼번에 콜백함수 안에 넣을(구현할) 수 있다.
*/
public class LoginLogic extends AsyncTask<String, Void, String> {
    // 앱에서 입력한 아이디와 비번을 담아서 톰캣 서버에 전달하기 위한 변수 선언.
    String sendMsg = null;
    // 톰캣 서버에 의해 처리된 결과를 받아서 담을 변수 선언.
    String receiveMsg = null;
    // 반드시 재정의해야 하는 메소드이다.
    // 백그라운드에서 실행할 코드를 포함하는 메소드이다.
    @Override
    protected String doInBackground(String... strings) {
        // 여기서 톰캣서버와 연동할 것이다.
        // URL Connection을 이용하여 처리할 예정.
        // ㄴ> 이 자체가 별도의 스레드에 해당이 된다.
        // UI 처리 스레드와 (오라클)통신을 위한 스레드가 따로 있고, 같이 쓸 수 없다.
        // 두 스레드가 충돌이 나지 않도록 코딩하는 것이 중요.
        // String apiURL = "http://192.168.0.163:7000/android/androidOracleConnection.jsp"; // localhost는 안 됨.
        String apiURL = "http://192.168.0.163:7000/login/jsonLogin";
        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestProperty("Connection-Type", "application/x-www-form-urlencoded");
            con.setRequestMethod("POST");
            // 톰캣에 보내기 위해 필요한 코드
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());
            Log.i("LoginLogic", "con: "+con.toString());
            // 톰캣 서버에 전송할 메세지 처리
            // strings: 파라미터. 열거형 연산자. 배열처럼 생각하고 사용하면 됨.
            sendMsg = "mem_id="+strings[0]+"&mem_pw="+strings[1];
            osw.write(sendMsg);
            // 이종 간이므로 flush한다.
            osw.flush();
            int responseCode = con.getResponseCode();   // 200, 204, 404, 500
            Log.i("LoginLogic", "responseCode: "+responseCode);
            BufferedReader br = null;
            if(responseCode == con.HTTP_OK) { // HTTP_OK = 200
                br = new BufferedReader(
                        new InputStreamReader(
                                con.getInputStream()
                                , "UTF-8"));
                String inputLine = null;
                StringBuilder sb_res = new StringBuilder();
                while((inputLine=br.readLine())!=null){
                    sb_res.append(inputLine);
                }
                receiveMsg = sb_res.toString();
                Log.i("LoginLogic", "receiveMsg: "+receiveMsg);
            }
        } catch (Exception e){
            e.toString();
            Log.i("LoginLogic", "Exception: "+e.toString());
        }
        return receiveMsg;
    }
}
