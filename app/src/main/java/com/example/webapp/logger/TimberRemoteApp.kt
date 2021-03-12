package com.example.webapp.logger

import android.annotation.SuppressLint
import android.app.Application
import android.provider.Settings
import com.example.webapp.data.DeviceDetails
import com.google.firebase.BuildConfig
import timber.log.Timber

class TimberRemoteApp : Application() {

    @SuppressLint("HardwareIds")
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            val deviceId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
            val deviceDetails = DeviceDetails(deviceId)
            val remoteTree = TimberRemoteTree(deviceDetails)

            Timber.plant(remoteTree)
        } else {
            //TODO plant timber release tree.
        }
    }
}