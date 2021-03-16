package com.example.webapp

import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import com.example.webapp.data.DeviceDetails
import com.example.webapp.logger.TimberRemoteTree

class LogoutService : Service() {
    companion object {
        var timer: CountDownTimer? = null
        private const val TAG = "LogoutService"
    }

    override fun onCreate() {
        super.onCreate()
        timer = object : CountDownTimer(1 * 60 * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                //Some code
                TimberRemoteTree(DeviceDetails()).i( "Service Started")
            }

            override fun onFinish() {
                TimberRemoteTree(DeviceDetails()).i( "Call Logout by Service")
                // Code for Logout
                stopSelf()
            }
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }


}