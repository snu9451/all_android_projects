package com.example.lotto80

import android.app.Application
import android.content.Context

class Lotto80Appllication: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this;
    }

    // 앱이 죽을 때 호출되는 메소드임
    override fun onTerminate() {
        super.onTerminate()
        appContext = null
    }

    companion object {
        var appContext: Context? = null
        private set
    }
}