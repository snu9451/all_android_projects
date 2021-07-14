package com.example.ktfcmkey801

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {

    // 토큰이 변경될 때마다 여기에서 서버에 변경을 해주어야 한다.
    // 실제 라이브 서버를 운영할 때는 onToken을 오버라이드해서 토큰이 갱신될 때마다
    // 서버에다가 해당 토큰을 갱신해주는 역할을 한다.
   override fun onNewToken(p0: String){
        super.onNewToken(p0)
        Log.i("태그", "Represhed token: " + p0)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        createNotificationChannel()
        // 타이틀 받아오기
        val title = remoteMessage.data["title"]
        val message = remoteMessage.data["mesage"]
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        NotificationManagerCompat.from(this)
            .notify(1, notificationBuilder.build())

    }

    // 채널 만들어 주기 완성
    // 채널을 생성하면 그 전에 설치한 앱은 삭제 후 재설치해야 함
    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //NotificationManager.IMPORTANCE_DEFAULT채널 중요도 설정해줌.
            var channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            //만들어진 채널을 Notification매니저에 추가해줌.
            channel.description = CHANNEL_DESCRIPTION
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
        }
    }

    // 코틀린에는 static이 없는 대신, companion object를 선언할 수 있음
    companion object {
        private const val CHANNEL_NAME = "안녕안녕이름"
        private const val CHANNEL_DESCRIPTION = "설명입니다"
        private const val CHANNEL_ID = "myID"
    }
}